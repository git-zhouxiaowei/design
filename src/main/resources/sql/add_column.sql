ALTER TABLE `表名`  ADD  `create_by` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '创建人';
ALTER TABLE `表名`  ADD  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间';
ALTER TABLE `表名`  ADD  `update_by` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '更新人';
ALTER TABLE `表名`  ADD  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间';