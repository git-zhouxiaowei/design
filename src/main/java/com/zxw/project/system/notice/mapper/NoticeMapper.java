package com.zxw.project.system.notice.mapper;

import com.zxw.project.system.notice.domain.Notice;

import java.util.List;

/**
 * 公告 数据层
 *
 * @author Zhouxw
 */
public interface NoticeMapper {
    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public Notice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<Notice> selectNoticeList(Notice notice);

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(Notice notice);

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(Notice notice);

    /**
     * 批量删除公告
     *
     * @param noticeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteNoticeByIds(String[] noticeIds);

    /**
     * 查询上一条通知信息
     *
     * @param noticeId 通知ID
     * @return 通知信息
     */
    Notice selectPreNoticeById(Long noticeId);

    /**
     * 查询下一条通知信息
     *
     * @param noticeId 通知ID
     * @return 通知信息
     */
    Notice selectNextNoticeById(Long noticeId);
}
