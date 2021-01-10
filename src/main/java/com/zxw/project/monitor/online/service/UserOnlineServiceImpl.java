package com.zxw.project.monitor.online.service;

import java.io.Serializable;
import java.util.Date;
import java.util.Deque;
import java.util.List;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxw.common.constant.ShiroConstants;
import com.zxw.common.utils.DateUtils;
import com.zxw.common.utils.StringUtils;
import com.zxw.framework.shiro.session.OnlineSessionDAO;
import com.zxw.project.monitor.online.domain.UserOnline;
import com.zxw.project.monitor.online.mapper.UserOnlineMapper;

/**
 * 在线用户 服务层处理
 *
 * @author Zhouxw
 */
@Service
public class UserOnlineServiceImpl implements IUserOnlineService {
    @Autowired
    private UserOnlineMapper userOnlineDao;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    @Autowired
    private EhCacheManager ehCacheManager;

    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public UserOnline selectOnlineById(String sessionId) {
        return userOnlineDao.selectOnlineById(sessionId);
    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public void deleteOnlineById(String sessionId) {
        UserOnline userOnline = selectOnlineById(sessionId);
        if (StringUtils.isNotNull(userOnline)) {
            userOnlineDao.deleteOnlineById(sessionId);
        }
    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    @Override
    public void batchDeleteOnline(List<String> sessions) {
        for (String sessionId : sessions) {
            UserOnline userOnline = selectOnlineById(sessionId);
            if (StringUtils.isNotNull(userOnline)) {
                userOnlineDao.deleteOnlineById(sessionId);
            }
        }
    }

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     */
    @Override
    public void saveOnline(UserOnline online) {
        userOnlineDao.saveOnline(online);
    }

    /**
     * 查询会话集合
     *
     * @param pageUtilEntity 分页参数
     */
    @Override
    public List<UserOnline> selectUserOnlineList(UserOnline userOnline) {
        return userOnlineDao.selectUserOnlineList(userOnline);
    }

    /**
     * 强退用户
     *
     * @param sessionId 会话ID
     */
    @Override
    public void forceLogout(String sessionId) {
        Session session = onlineSessionDAO.readSession(sessionId);
        if (session == null) {
            return;
        }
        session.setTimeout(1000);
        userOnlineDao.deleteOnlineById(sessionId);
    }

    /**
     * 清理用户缓存
     *
     * @param loginName 登录名称
     * @param sessionId 会话ID
     */
    @Override
    public void removeUserCache(String loginName, String sessionId) {
        Cache<String, Deque<Serializable>> cache = ehCacheManager.getCache(ShiroConstants.SYS_USERCACHE);
        Deque<Serializable> deque = cache.get(loginName);
        if (StringUtils.isEmpty(deque) || deque.size() == 0) {
            return;
        }
        deque.remove(sessionId);
    }

    /**
     * 查询会话集合
     *
     * @param online 会话信息
     */
    @Override
    public List<UserOnline> selectOnlineByExpired(Date expiredDate) {
        String lastAccessTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, expiredDate);
        return userOnlineDao.selectOnlineByExpired(lastAccessTime);
    }
}