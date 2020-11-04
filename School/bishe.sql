/*
Navicat MySQL Data Transfer

Source Server         : Yyt_Mysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : bishe

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-06-06 20:31:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for enterprise_info
-- ----------------------------
DROP TABLE IF EXISTS `enterprise_info`;
CREATE TABLE `enterprise_info` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `name` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `mobile` varchar(50) DEFAULT NULL COMMENT '公司电话',
  `birth_date` date DEFAULT NULL COMMENT '公司成立时间',
  `address` varchar(50) DEFAULT NULL COMMENT '公司地址',
  `introduction` varchar(300) DEFAULT NULL COMMENT '介绍',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  `region` varchar(20) DEFAULT NULL COMMENT '地区',
  `logo_url` varchar(255) DEFAULT NULL COMMENT 'logo地址',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `wechat_id` varchar(50) DEFAULT NULL COMMENT '面试官微信号',
  `status` varchar(5) DEFAULT NULL COMMENT '状态',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `audit_by` varchar(50) DEFAULT NULL COMMENT '审核人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of enterprise_info
-- ----------------------------
INSERT INTO `enterprise_info` VALUES ('69504970608607232', '69504970025598976', '百度', '1477855747', '2010-02-21', '百度地址', '就是个做搜索的。。。', '上海', '青浦', '', '2019-05-18 11:07:56', 'baidu', 'baiduwechat2222222', '审核通过', '2020-05-29 19:59:20', 'admin');
INSERT INTO `enterprise_info` VALUES ('70636759469260800', '70636759272128512', '阿里巴巴', '15898788747', '2019-05-22', '阿里地址', '送送送', '北京', '啦啦啦', null, '2019-05-21 14:05:16', 'alibaba', null, '审核通过', null, 'admin');
INSERT INTO `enterprise_info` VALUES ('70650823016710144', '70650822953795584', '腾讯', '15866988747', '2005-04-30', '杀敌发', '胜多负少', '后爪子', '上房顶', 'http://pic2.52pk.com/files/140219/1288606_102738_1.jpg', '2019-05-21 15:01:09', 'tengxun', 'sdfsdf', '审核通过', '2020-04-22 21:46:59', 'admin');
INSERT INTO `enterprise_info` VALUES ('70991904258392064', '70991901846667264', '华为', '15866988747', '1990-10-09', 'xxx', '加班 加班 加班', 'xx', 'xx', null, '2019-05-22 13:36:29', 'huawei', null, '审核通过', null, 'admin');
INSERT INTO `enterprise_info` VALUES ('192393842954076160', '192393842937298944', '字节跳动', '15899877477', '2020-05-01', 'sdssfd', 'sdfsdfsdfsdfsdfsdfsdfsdf', 'sdf', 'sdf', '', '2020-04-21 21:44:27', 'zijietiaodong', '23454333333', '审核通过', '2020-04-22 20:38:26', 'admin');
INSERT INTO `enterprise_info` VALUES ('192742301632561152', '192742301171187712', '搜狗', '15877888544', '2016-04-01', '不知道', '我也是个做搜索的，虽然索', '城市', '区域', '', '2020-04-22 20:49:06', 'sougou', 'wxh114447447777sdfsdf', '审核通过', '2020-05-29 19:59:22', 'admin');

-- ----------------------------
-- Table structure for explain_meeting
-- ----------------------------
DROP TABLE IF EXISTS `explain_meeting`;
CREATE TABLE `explain_meeting` (
  `id` bigint(20) NOT NULL,
  `enterprise_id` bigint(20) DEFAULT NULL COMMENT '企业ID',
  `title` varchar(50) DEFAULT NULL COMMENT '宣讲会标题',
  `introduction` varchar(255) DEFAULT NULL COMMENT '宣讲会简介',
  `poster_url` varchar(255) DEFAULT NULL COMMENT '宣传海报图片地址',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `publish_date` datetime DEFAULT NULL COMMENT '消息发布时间',
  `qr_code` varchar(255) DEFAULT NULL COMMENT '进入宣讲二维码地址',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `modify_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of explain_meeting
-- ----------------------------
INSERT INTO `explain_meeting` VALUES ('192022179389374464', '69504970608607232', '2020年百度宣讲会', '适当方式的方式发水电费水电费水电费水电费水电费双方都水电费是的冯绍峰的是', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587397905213&di=643416f0019d20cda1a940345dcb7eed&imgtype=0&src=http%3A%2F%2Ft9.baidu.com%2Fit%2Fu%3D3363001160%2C1163944807%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D830', '2020-05-30 09:00:00', '2020-05-30 11:00:00', '2020-04-20 21:07:35', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3826646743,4046416846&fm=26&gp=0.jpg', '2020-04-20 21:07:35', 'baidu', null, null);
INSERT INTO `explain_meeting` VALUES ('192023127826366464', '69504970608607232', '第三方贵航股份VDC', '胜多负少是大是大非佛山市第三方第三方', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587397905213&di=643416f0019d20cda1a940345dcb7eed&imgtype=0&src=http%3A%2F%2Ft9.baidu.com%2Fit%2Fu%3D3363001160%2C1163944807%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D830', '2020-05-30 13:00:00', '2020-05-30 15:00:00', '2020-04-20 21:11:21', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3826646743,4046416846&fm=26&gp=0.jpg', '2020-04-20 21:11:21', 'baidu', null, null);
INSERT INTO `explain_meeting` VALUES ('192729570149924864', '192393842954076160', '是否水电费', '杀敌发是东风风神', 'https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1587556626&di=e2fa187be8e79d12d1b73b548f484693&src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg', '2020-05-30 13:15:41', '2020-05-31 16:00:00', '2020-04-22 19:58:30', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3381390367,3810581293&fm=26&gp=0.jpg', '2020-04-22 19:58:30', 'zijietiaodong', null, null);
INSERT INTO `explain_meeting` VALUES ('192741336351244288', '69504970608607232', '百度宣讲打会', '水电费水电费水电费是否是地方大发送到发送到', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587569541167&di=16dd4fe4dc948ba556bafc179275929d&imgtype=0&src=http%3A%2F%2Ft7.baidu.com%2Fit%2Fu%3D2789097066%2C2241155970%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D959', '2020-05-30 14:00:00', '2020-05-31 16:15:00', '2020-04-22 20:45:16', 'http://img0.imgtn.bdimg.com/it/u=2809323176,1819959356&fm=11&gp=0.jpg', '2020-04-22 20:45:16', 'baidu', null, null);
INSERT INTO `explain_meeting` VALUES ('206111186943475712', '69504970608607232', 'qwe', '123qwe', '123qwe', '2020-05-30 00:00:00', '2020-06-10 00:00:00', '2020-05-29 18:12:16', 'q', '2020-05-29 18:12:16', 'baidu', null, null);

-- ----------------------------
-- Table structure for hunter_experience
-- ----------------------------
DROP TABLE IF EXISTS `hunter_experience`;
CREATE TABLE `hunter_experience` (
  `id` bigint(20) NOT NULL,
  `experience_type` varchar(20) DEFAULT NULL COMMENT '经历类型',
  `hunter_id` bigint(20) DEFAULT NULL,
  `experience_content` varchar(50) DEFAULT NULL COMMENT '经历内容',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of hunter_experience
-- ----------------------------
INSERT INTO `hunter_experience` VALUES ('203598071458693120', 'XM', '197336357180276736', 'e\'e\'e', '2020-05-22 00:00:00', '2020-05-22 00:00:00');
INSERT INTO `hunter_experience` VALUES ('203598071941038080', 'GZ', '197336357180276736', '2222', '2020-05-08 00:00:00', '2020-06-15 00:00:00');
INSERT INTO `hunter_experience` VALUES ('203598071966203904', 'JY', '197336357180276736', '1111', '2020-05-09 00:00:00', '2020-05-09 00:00:00');

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` bigint(20) NOT NULL,
  `enterprise_id` bigint(20) DEFAULT NULL COMMENT '企业Id',
  `name` varchar(20) DEFAULT NULL COMMENT '职位名称',
  `experience_start` int(11) DEFAULT NULL COMMENT '要求工作年限开始',
  `experience_end` int(11) DEFAULT NULL,
  `education` varchar(20) DEFAULT NULL COMMENT '学历最低水平',
  `tag` varchar(50) DEFAULT NULL COMMENT '职位标签',
  `temptation` varchar(200) DEFAULT NULL COMMENT '职位诱惑',
  `introduction` varchar(1000) DEFAULT NULL COMMENT '职位描述',
  `published_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `published_by` varchar(255) DEFAULT NULL COMMENT '发布人',
  `published_id` bigint(20) DEFAULT NULL,
  `active_flg` varchar(1) DEFAULT NULL,
  `work_address` varchar(50) DEFAULT NULL COMMENT '工作地址',
  `pay_start` double DEFAULT NULL,
  `pay_end` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('69871423975325696', '69504970608607232', 'Java中级工程师', '3', '5', '研究生', 'JAVA,后端开发,vue.js,架构,.net', '才是第三方是否水电费是的冯绍峰', '杀敌发上房顶是否是发生的是的冯绍峰的是大是大非', '2020-04-22 20:42:59', 'baidu', '69504970025598976', '2', '河南郑州刚是的冯绍峰', '0', '0');
INSERT INTO `job` VALUES ('69872135740325888', '69504970608607232', 'java高级工程师', '5', '7', '2', 'JAVA,后端开发', '是的冯绍峰的好的非官方的', '是的冯绍峰是的大富大贵', '2019-05-19 11:28:13', 'baidu', '69504970025598976', '2', '杭州哈哈哈', null, null);
INSERT INTO `job` VALUES ('70650011108507648', '70636759469260800', '算法工程师', '7', '10', '4', 'C,后端开发,JAVA,架构,Angularjs', '苏打水', '是的冯绍峰', '2019-05-26 12:14:14', 'alibaba', '70636759272128512', '2', '苏州金鸡湖', '8000', '10000');
INSERT INTO `job` VALUES ('70651174621020160', '70650823016710144', 'java初级工程师', '0', '3', '2', 'JAVA,后端开发,vue.js,Angularjs', '996', '996', '2019-05-21 15:06:03', 'tengxun', '70650822953795584', '2', '常州大学城', null, null);
INSERT INTO `job` VALUES ('70651344364503040', '70650823016710144', '初级测试工程师', '0', '3', '1', '测试', '测试也是996', '测试也是996', '2019-05-21 15:06:05', 'tengxun', '70650822953795584', '2', '北京大兴', null, null);
INSERT INTO `job` VALUES ('70992470548152320', '70991904258392064', 'java初级开发', '0', '3', '1', 'JAVA,后端开发,vue.js', '996', '996', '2019-05-22 13:41:50', 'huawei', '70991901846667264', '2', '上海篁不想', null, null);
INSERT INTO `job` VALUES ('71356979737526272', '70636759469260800', 'java', '0', '2', '3', '', '', '', null, 'alibaba', '70636759272128512', '1', '成都弟弟', null, null);
INSERT INTO `job` VALUES ('190922669879721984', '70650823016710144', '33333', null, null, '本科', '', '', '3333', null, 'tengxun', '70650822953795584', '1', '', '0', '0');
INSERT INTO `job` VALUES ('190923183723905024', '70650823016710144', 'sdf', null, null, '研究生', '', 'sdfsdfe', 'sfsdf', null, 'tengxun', '70650822953795584', '1', '', '0', '0');
INSERT INTO `job` VALUES ('190944001585577984', '70650823016710144', '第三方', null, null, '研究生', '', '水电费水电费', '是的冯绍峰是', null, 'tengxun', '70650822953795584', '1', '是大是大非', '0', '0');
INSERT INTO `job` VALUES ('190944001585577999', '70650823016710144', '第三方', null, null, '研究生', '', '水电费水电费', '是的冯绍峰是', null, 'tengxun', '70650822953795584', '1', '是大是大非', '0', '0');
INSERT INTO `job` VALUES ('192725713332207616', '192393842954076160', 'java程序员', null, null, '大专', ',ios,Go,C++', '大厂福利好', '做xx事业部的业务开发', null, 'zijietiaodong', '192393842937298944', '1', '上海xxxxxx', '0', '0');
INSERT INTO `job` VALUES ('192728873505390592', '192393842954076160', '胜多负少', null, null, '大专', 'JAVA,.net,C,C++,Go,ios,android', '是的冯绍峰的', '水电费水电费', null, 'zijietiaodong', '192393842937298944', '1', '水电费水电费', '0', '0');
INSERT INTO `job` VALUES ('192740940643827712', '69504970608607232', '算法工程师', null, null, '研究生', 'C,C++,python', '大厂', '算法工程师介绍 blabalblal', null, 'baidu', '69504970025598976', '1', 'ssss', '0', '0');
INSERT INTO `job` VALUES ('197381630468292608', '69504970608607232', '11', null, null, '本科', '.net', '1', '11', null, 'baidu', '69504970025598976', '1', '1', '0', '0');
INSERT INTO `job` VALUES ('208977559017226240', '69504970608607232', '11', null, null, '大专', 'android,C++', '', '11', null, 'baidu', '69504970025598976', '1', '11', '0', '0');

-- ----------------------------
-- Table structure for job_hunter
-- ----------------------------
DROP TABLE IF EXISTS `job_hunter`;
CREATE TABLE `job_hunter` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `gender` varchar(1) DEFAULT NULL COMMENT '性别（1-男，0-女）',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `birth_date` date DEFAULT NULL COMMENT '生日',
  `work_year` int(11) DEFAULT NULL COMMENT '工作年限',
  `education` varchar(5) DEFAULT NULL COMMENT '学历',
  `college_name` varchar(20) DEFAULT NULL COMMENT '大学名称',
  `last_company` varchar(20) DEFAULT NULL COMMENT '上家公司名称',
  `self_introduction` varchar(300) DEFAULT NULL COMMENT '自我介绍',
  `remark` varchar(50) DEFAULT NULL COMMENT '一句话描述自己',
  `can_search` varchar(1) DEFAULT NULL COMMENT '是否可以被查看',
  `tag` varchar(50) DEFAULT NULL COMMENT '面试倾向',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL COMMENT '电子邮箱',
  `wechat_id` varchar(255) DEFAULT NULL COMMENT '微信号',
  `image_url` varchar(255) DEFAULT NULL COMMENT '照片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_hunter
-- ----------------------------
INSERT INTO `job_hunter` VALUES ('69505057615249408', '69505057602666496', '傅一鸣', '1', '15862311715', '1993-06-03', '4', '本科', '苏州科技大学', '金螳螂', '我就是我，懂前端的后端工程师', '杀敌发', 'Y', 'JAVA,vue,Go,android', '2019-05-18 11:08:17', 'fuyiming', '1822905066@qq.com', 'sdf323232333333333', '74557757415489536.PNG');
INSERT INTO `job_hunter` VALUES ('70632813459668992', '70632812201377792', null, null, null, null, null, null, null, null, null, null, 'N', null, '2019-05-21 13:49:35', 'sudaqiang', null, null, null);
INSERT INTO `job_hunter` VALUES ('70650755236757504', '70650754716663808', '周公子', null, '15877477587', '1992-12-31', '2', '本科', '南京理工', 'xxx', '巴拉巴拉巴拉拉拉吧', '未知数', 'Y', 'JAVA,vue.js', '2019-05-21 15:00:53', 'zhougongzi', null, null, null);
INSERT INTO `job_hunter` VALUES ('70992054229925888', '70992053974073344', '任正非小迷弟', null, '15899877474', '1992-05-04', '2', '本科', '南京理工', '电信', '996', '996', 'Y', 'JAVA,vue.js,后端开发,Angularjs', '2019-05-22 13:37:05', 'rzfxiaomidi', null, null, null);
INSERT INTO `job_hunter` VALUES ('71353478559039488', '71353478542262272', null, null, null, null, null, null, null, null, null, null, 'N', null, '2019-05-23 13:33:15', '贾诩', null, null, null);
INSERT INTO `job_hunter` VALUES ('71353759569018880', '71353759552241664', '雷神', null, '15866774747', null, null, null, null, null, null, null, 'N', null, '2019-05-23 13:34:22', '雷神', null, null, null);
INSERT INTO `job_hunter` VALUES ('192391662511587328', '192391661605617664', 'fym', '1', null, null, null, '博士', 'sdsdf', null, null, null, 'N', null, '2020-04-21 21:35:47', '333', '1822635855@qq.com', null, null);
INSERT INTO `job_hunter` VALUES ('192393558014033920', '192393557930147840', '胡大华', '1', '15866699887', null, null, '本科', '承德公馆', null, null, null, 'N', null, '2020-04-21 21:43:19', 'fym2', '157777555@qq.com', null, null);
INSERT INTO `job_hunter` VALUES ('192739907511255040', '192739907213459456', '苏东坡', '1', '15866988777', '2020-04-09', null, '博士', '东坡大学', '', '问问吾问无为谓无', '', 'N', ',JAVA,vue', '2020-04-22 20:39:35', 'sudongpo', 'sdp123456@163.com', '222222222222222222', '');
INSERT INTO `job_hunter` VALUES ('197336357180276736', '197336357108973568', '游月童', '1', '18849019079', '2020-05-04', null, '研究生', '北交大海滨学院', '', '123123', '', 'N', ',JAVA', '2020-05-05 00:00:00', 'yyt', '18849019079@163.com', '123', '');
INSERT INTO `job_hunter` VALUES ('205763542853353472', '205763542740107264', null, '1', '18849019079', null, null, '大专', '11', null, null, null, 'N', null, '2020-05-28 19:10:51', 'yyt1', '123123', null, null);
INSERT INTO `job_hunter` VALUES ('205767686485966848', '205767686448218112', null, null, '', null, null, null, null, null, null, null, 'N', null, '2020-05-28 19:27:19', '', '', null, null);
INSERT INTO `job_hunter` VALUES ('205768910690058240', '205768910660698112', null, null, '', null, null, null, null, null, null, null, 'N', null, '2020-05-28 19:32:11', '', '', null, null);
INSERT INTO `job_hunter` VALUES ('205772837972082688', '205772837942722560', null, '1', '11111118849', null, null, '大专', '11', null, null, null, 'N', null, '2020-05-28 19:47:47', 'qwe1231', '18849019079@163.com', null, null);

-- ----------------------------
-- Table structure for job_relation
-- ----------------------------
DROP TABLE IF EXISTS `job_relation`;
CREATE TABLE `job_relation` (
  `id` bigint(20) NOT NULL,
  `job_id` bigint(20) DEFAULT NULL COMMENT '职位Id',
  `hunter_id` bigint(20) DEFAULT NULL COMMENT '求职人Id',
  `interview_id` bigint(20) DEFAULT NULL COMMENT '面试官ID',
  `apply_time` timestamp NULL DEFAULT NULL COMMENT '申请时间',
  `can_interview` varchar(1) DEFAULT NULL COMMENT '是否邀请面试',
  `interview_time` varchar(25) DEFAULT '' COMMENT '邀请面试时间',
  `accept_interview` varchar(1) DEFAULT NULL COMMENT '是否接收面试',
  `pass_flag` varchar(1) DEFAULT NULL COMMENT '是否通过面试',
  `interview_evaluation` varchar(100) DEFAULT NULL COMMENT '面试评价',
  `evaluation_time` timestamp NULL DEFAULT NULL COMMENT '评价时间',
  `sign_flag` varchar(1) DEFAULT NULL COMMENT '是否签约（Y-签约，N-不签约）',
  `sign_pic_url` varchar(255) DEFAULT NULL COMMENT '云签约图片地址',
  `sign_date` datetime DEFAULT NULL COMMENT '签约时间',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_relation
-- ----------------------------
INSERT INTO `job_relation` VALUES ('69871600157065216', '69871423975325696', '69505057615249408', null, null, 'I', '热热翁', 'N', null, null, null, null, null, null, '2019-05-19 11:25:04');
INSERT INTO `job_relation` VALUES ('69872609059143680', '69872135740325888', '69505057615249408', '69504970025598976', null, 'I', '5月20日早上十点', 'N', 'Y', '真不怎么样', '2019-05-20 14:35:34', '1', null, null, '2019-05-19 11:28:56');
INSERT INTO `job_relation` VALUES ('69906867215663104', '69872135740325888', '69505057615249408', '69504970025598976', '2019-05-19 13:44:56', 'I', '东方闪电', 'Y', 'N', '胜多负少', '2020-04-19 22:07:01', null, null, null, '2019-05-19 13:44:56');
INSERT INTO `job_relation` VALUES ('69906977249034240', '69872135740325888', '69505057615249408', '69504970025598976', '2019-05-19 13:45:22', 'R', null, null, null, null, null, null, null, null, '2019-05-19 13:45:22');
INSERT INTO `job_relation` VALUES ('69907054004797440', '69872135740325888', '69505057615249408', '69504970025598976', '2019-05-19 13:45:40', 'R', null, 'N', null, null, null, null, null, null, '2019-05-19 13:45:40');
INSERT INTO `job_relation` VALUES ('69908433045815296', '69872135740325888', '69505057615249408', '69504970025598976', '2019-05-19 13:51:09', 'I', '是方法杀敌发', 'Y', 'Y', 'sdfsdf', '2020-04-19 22:37:09', '3', null, '2020-04-19 22:36:11', '2019-05-19 13:51:09');
INSERT INTO `job_relation` VALUES ('69922407959232512', '69872135740325888', '69505057615249408', '69504970025598976', '2019-05-19 14:46:41', 'I', '测试', 'Y', 'Y', '适当方式的方式的', '2020-04-19 22:07:26', '1', null, null, '2019-05-19 14:46:41');
INSERT INTO `job_relation` VALUES ('69922635487641600', '69871423975325696', '69505057615249408', null, null, 'I', '34234', 'Y', null, null, null, null, null, null, '2019-05-19 14:47:35');
INSERT INTO `job_relation` VALUES ('70265925105876992', '69871423975325696', '69505057615249408', null, null, 'I', '明早', null, null, null, null, null, null, null, '2019-05-20 13:31:42');
INSERT INTO `job_relation` VALUES ('70650097221763072', '70650011108507648', '69505057615249408', '70636759272128512', null, 'I', '是的冯绍峰的', 'N', null, null, null, null, null, null, '2019-05-21 14:58:16');
INSERT INTO `job_relation` VALUES ('70652154083278848', '70651344364503040', '70650755236757504', '70650822953795584', '2019-05-21 15:06:26', 'I', '测试', 'N', null, null, null, null, null, null, '2019-05-21 15:06:26');
INSERT INTO `job_relation` VALUES ('70652184076746752', '70651174621020160', '70650755236757504', '70650822953795584', '2019-05-21 15:06:33', 'I', '五月22日早上十点', 'Y', 'Y', 'sdfs', '2019-05-21 15:16:23', null, null, null, '2019-05-21 15:06:33');
INSERT INTO `job_relation` VALUES ('70993411959685120', '70992470548152320', '70992054229925888', '70991901846667264', '2019-05-22 13:42:28', 'I', '五月23日晚上9点', 'Y', 'Y', '华为 加油', '2019-05-22 13:44:53', null, null, null, '2019-05-22 13:42:28');
INSERT INTO `job_relation` VALUES ('191583616638124032', '69871423975325696', '69505057615249408', '69504970025598976', '2020-04-19 16:04:54', 'I', '水电费水电费', 'Y', 'Y', null, null, null, null, null, '2020-04-19 16:04:54');
INSERT INTO `job_relation` VALUES ('192045451984240640', '190923183723905024', '69505057615249408', '70650822953795584', '2020-04-20 22:40:04', null, null, null, null, null, null, null, null, null, '2020-04-20 22:40:04');
INSERT INTO `job_relation` VALUES ('192740273724325888', '192728873505390592', '192739907511255040', '192393842937298944', '2020-04-22 20:41:02', null, null, null, null, null, null, null, null, null, '2020-04-22 20:41:02');
INSERT INTO `job_relation` VALUES ('192740455597735936', '69871423975325696', '192739907511255040', '69504970025598976', '2020-04-22 20:41:46', 'I', '邀请你微信面试，先加微信22222222', 'Y', 'Y', '好公司我去了', '2020-04-22 20:47:47', '3', null, '2020-04-22 20:47:49', '2020-04-22 20:41:46');
INSERT INTO `job_relation` VALUES ('197336901407997952', '69871423975325696', '197336357180276736', '69504970025598976', '2020-05-05 13:06:23', 'R', null, null, null, null, null, null, null, null, '2020-05-05 13:06:23');
INSERT INTO `job_relation` VALUES ('197336911319138304', '69871423975325696', '197336357180276736', '69504970025598976', '2020-05-05 13:06:26', 'I', '1111', 'Y', null, null, null, null, null, null, '2020-05-05 13:06:26');
INSERT INTO `job_relation` VALUES ('197348844986433536', '69871423975325696', '197336357180276736', '69504970025598976', '2020-05-05 13:53:51', 'I', null, 'Y', 'Y', null, null, '3', null, '2020-05-05 15:31:14', '2020-05-05 13:53:51');
INSERT INTO `job_relation` VALUES ('197349033256157184', '69871423975325696', '197336357180276736', '69504970025598976', '2020-05-05 13:54:36', 'I', null, 'Y', null, null, null, null, null, null, '2020-05-05 13:54:36');
INSERT INTO `job_relation` VALUES ('197351075760570368', '192740940643827712', '197336357180276736', '69504970025598976', '2020-05-05 14:02:43', 'I', null, null, null, null, null, null, null, null, '2020-05-05 14:02:43');
INSERT INTO `job_relation` VALUES ('203599332920786944', '69871423975325696', '197336357180276736', '69504970025598976', '2020-05-22 19:51:03', null, null, null, null, null, null, null, null, null, '2020-05-22 19:51:03');
INSERT INTO `job_relation` VALUES ('205751145312813056', '69871423975325696', '197336357180276736', '69504970025598976', '2020-05-28 18:21:35', null, null, null, null, null, null, null, null, null, '2020-05-28 18:21:35');
INSERT INTO `job_relation` VALUES ('205763683677110272', '70650011108507648', '205763542853353472', '70636759272128512', '2020-05-28 19:11:25', null, null, null, null, null, null, null, null, null, '2020-05-28 19:11:25');
INSERT INTO `job_relation` VALUES ('206320380539830272', '69872135740325888', '197336357180276736', '69504970025598976', '2020-05-30 08:03:31', null, null, null, null, null, null, null, null, null, '2020-05-30 08:03:31');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `login_name` varchar(20) DEFAULT NULL COMMENT '登录名',
  `user_type` varchar(5) DEFAULT NULL COMMENT '用户类型',
  `password` varchar(36) DEFAULT NULL COMMENT '密码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `active_flg` varchar(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('69504740060299264', 'admin', 'A', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-18 11:07:01', null);
INSERT INTO `user` VALUES ('69504970025598976', 'baidu', 'E', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-18 11:07:56', null);
INSERT INTO `user` VALUES ('69505057602666496', 'fuyiming', 'H', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-18 11:08:17', null);
INSERT INTO `user` VALUES ('70632812201377792', 'sudaqiang', 'H', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-21 13:49:35', null);
INSERT INTO `user` VALUES ('70636759272128512', 'alibaba', 'E', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-21 14:05:16', null);
INSERT INTO `user` VALUES ('70650754716663808', 'zhougongzi', 'H', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-21 15:00:53', null);
INSERT INTO `user` VALUES ('70650822953795584', 'tengxun', 'E', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-21 15:01:09', null);
INSERT INTO `user` VALUES ('70991901846667264', 'huawei', 'E', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-22 13:36:28', null);
INSERT INTO `user` VALUES ('70992053974073344', 'rzfxiaomidi', 'H', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-22 13:37:05', null);
INSERT INTO `user` VALUES ('71353392433201152', '奥巴马', 'A', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-23 13:32:54', null);
INSERT INTO `user` VALUES ('71353478542262272', '贾诩', 'H', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-23 13:33:15', null);
INSERT INTO `user` VALUES ('71353759552241664', '雷神', 'H', 'e10adc3949ba59abbe56e057f20f883e', '2019-05-23 13:34:22', null);
INSERT INTO `user` VALUES ('192391661605617664', '333', 'H', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-21 21:35:47', null);
INSERT INTO `user` VALUES ('192393495481155584', 'fym', 'H', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-21 21:43:04', null);
INSERT INTO `user` VALUES ('192393557930147840', 'fym2', 'H', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-21 21:43:19', null);
INSERT INTO `user` VALUES ('192393842937298944', 'zijietiaodong', 'E', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-21 21:44:27', null);
INSERT INTO `user` VALUES ('192739907213459456', 'sudongpo', 'H', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-22 20:39:35', null);
INSERT INTO `user` VALUES ('192742301171187712', 'sougou', 'E', 'e10adc3949ba59abbe56e057f20f883e', '2020-04-22 20:49:06', null);
INSERT INTO `user` VALUES ('197336357108973568', 'yyt', 'H', 'e10adc3949ba59abbe56e057f20f883e', '2020-05-05 13:04:13', null);
INSERT INTO `user` VALUES ('205772837942722560', 'qwe1231', 'H', '698d51a19d8a121ce581499d7b701668', '2020-05-28 19:47:47', null);
