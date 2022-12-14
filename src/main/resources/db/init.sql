-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- 主机： db
-- 生成日期： 2022-12-08 11:41:30
-- 服务器版本： 8.0.31
-- PHP 版本： 8.0.19

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `digger`
--

-- --------------------------------------------------------

--
-- 表的结构 `category_label`
--

CREATE TABLE `category_label`
(
    `id`       int NOT NULL,
    `category` varchar(7)  DEFAULT NULL,
    `label`    varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- 转存表中的数据 `category_label`
--

INSERT INTO `category_label` (`id`, `category`, `label`)
VALUES (1, '认罪/悔改', '悔改'),
       (2, '认罪/悔改', '软弱'),
       (3, '情绪/节奏', '自由'),
       (4, '情绪/节奏', '爱情爱慕'),
       (5, '救赎', '救恩'),
       (6, '主题', '住在基督里'),
       (7, '救赎', '代赎担罪代求替代'),
       (8, '神的护理和诫命', '成就'),
       (9, '颂赞', '赞美称颂歌颂歌唱'),
       (10, '新生命', '满足喜乐'),
       (11, '救赎', '福音'),
       (12, '新生命', '见证'),
       (13, '颂赞', '慈爱恩慈'),
       (14, '新生命', '感恩感谢称谢'),
       (15, '颂赞', '恩典怜悯忍耐'),
       (16, '主题', '祷告'),
       (17, '新生命', '顺服降伏'),
       (18, '救赎', '十字架'),
       (19, '救赎', '受苦受难受死'),
       (20, '情绪/节奏', '激励激昂'),
       (21, '情绪/节奏', '奇妙'),
       (22, '神', '耶稣基督圣子'),
       (23, '救赎', '复活'),
       (24, '认罪/悔改', '恨恶罪'),
       (25, '神的护理和诫命', '成圣'),
       (26, '救赎', '除罪洁净洗净'),
       (27, '颂赞', '圣洁'),
       (28, '认罪/悔改', '罪人认罪'),
       (29, '主题', '祈求'),
       (30, '神的权能', '应许'),
       (31, '新生命', '委身摆上'),
       (32, '神的权能', '预定拣选'),
       (33, '神的权能', '主权'),
       (34, '救赎', '救主拯救得救'),
       (35, '救赎', '饶恕赦免赦罪赎罪赎价'),
       (36, '主题', '工作'),
       (37, '节期仪式', '禁食'),
       (38, '节期仪式', '献祭'),
       (39, '节期仪式', '伯利恒'),
       (40, '节期仪式', '降生'),
       (41, '神的护理和诫命', '保守保护看顾'),
       (42, '新生命', '默想思想'),
       (43, '新生命', '爱主爱神'),
       (44, '新生命', '忠心'),
       (45, '新生命', '交托仰望'),
       (46, '神的护理和诫命', '供应'),
       (47, '颂赞', '荣耀'),
       (48, '新生命', '奉献'),
       (49, '神', '升天再来'),
       (50, '主题', '儿童'),
       (51, '神', '永恒不变'),
       (52, '神的护理和诫命', '饱足'),
       (53, '神的护理和诫命', '带领'),
       (54, '主题', '信心确据'),
       (55, '圣经经文', '诗篇'),
       (56, '情绪/节奏', '坚定'),
       (57, '新生命', '等候盼望'),
       (58, '新生命', '敬畏'),
       (59, '新生命', '倚靠依靠信靠'),
       (60, '神的护理和诫命', '引导引领'),
       (61, '情绪/节奏', '需要'),
       (62, '礼拜/宗教改革', '教导牧养'),
       (63, '神的护理和诫命', '平安'),
       (64, '情绪/节奏', '艰难患难苦难'),
       (65, '情绪/节奏', '安宁安静'),
       (66, '认罪/悔改', '试探'),
       (67, '认罪/悔改', '犯罪'),
       (68, '新生命', '友谊陪伴同行'),
       (69, '新生命', '精兵'),
       (70, '神的国', '天堂'),
       (71, '神的护理和诫命', '成全'),
       (72, '礼拜/宗教改革', '先贤历史'),
       (73, '礼拜/宗教改革', '圣徒圣民子民百姓'),
       (74, '认罪/悔改', '对付罪'),
       (75, '神的权能', '得胜'),
       (76, '新生命', '心愿心志'),
       (77, '新生命', '预备'),
       (78, '礼拜/宗教改革', '传福音大使命'),
       (79, '主题', '结出果子九种果子'),
       (80, '礼拜/宗教改革', '主餐圣餐'),
       (81, '礼拜/宗教改革', '纪念'),
       (82, '礼拜/宗教改革', '爱人如己彼此相爱'),
       (83, '新生命', '团契相交分享'),
       (84, '情绪/节奏', '忧愁忧伤哀伤沉重'),
       (85, '情绪/节奏', '爱慕渴慕渴望追求寻求'),
       (86, '主题', '光耀'),
       (87, '神的护理和诫命', '恩福蒙福蒙恩福分'),
       (88, '神的护理和诫命', '神的爱'),
       (89, '颂赞', '美善良善'),
       (90, '主题', '灵魂'),
       (91, '礼拜/宗教改革', '异象呼召 '),
       (92, '颂赞', '真光'),
       (93, '新生命', '接受接纳领受谦卑'),
       (94, '礼拜/宗教改革', '敬拜'),
       (95, '礼拜/宗教改革', '服事事奉'),
       (96, '神的权能', '根基磐石房角石'),
       (97, '礼拜/宗教改革', '真理根基'),
       (98, '神的权能', '末世审判'),
       (99, '新生命', '归主跟从跟随'),
       (100, '新生命', '争战挣扎重担'),
       (101, '礼拜/宗教改革', '教会群体会众'),
       (102, '神的国', '天路'),
       (103, '礼拜/宗教改革', '教会'),
       (104, '神的国', '锡安'),
       (105, '礼拜/宗教改革', '主日聚会'),
       (106, '主题', '临终葬礼告别'),
       (107, '神的国', '天国天家神的家'),
       (108, '主题', '魔鬼撒旦'),
       (109, '情绪/节奏', '欢快欢喜'),
       (110, '礼拜/宗教改革', '讲道'),
       (111, '神的护理和诫命', '恩赐'),
       (112, '新生命', '和好'),
       (113, '新生命', '殷勤'),
       (114, '礼拜/宗教改革', '共同合一'),
       (115, '新生命', '读经灵修门训'),
       (116, '神', '三一三位一体'),
       (117, '神', '上帝真神'),
       (118, '神的权能', '鉴察'),
       (119, '神的权能', '保障'),
       (120, '礼拜/宗教改革', '奋兴复兴'),
       (121, '神', '圣灵'),
       (122, '新生命', '决定决志立志'),
       (123, '神的护理和诫命', '祝福'),
       (124, '神的权能', '建造'),
       (125, '神的国', '圣殿宝座'),
       (126, '情绪/节奏', '黑暗'),
       (127, '神的权能', '力量'),
       (128, '神的权能', '刑罚'),
       (129, '主题', '婚礼结婚家庭'),
       (130, '神的权能', '无所不在'),
       (131, '神的权能', '掌权'),
       (132, '神的权能', '同在'),
       (133, '节期仪式', '圣诞圣婴'),
       (134, '颂赞', '智慧'),
       (135, '颂赞', '美丽美好'),
       (136, '颂赞', '创造'),
       (137, '主题', '天使'),
       (138, '神', '独一'),
       (139, '新生命', '不靠行为'),
       (140, '主题', '试炼'),
       (141, '礼拜/宗教改革', '圣礼洗礼'),
       (142, '神的护理和诫命', '普遍恩典'),
       (143, '颂赞', '信实'),
       (144, '神的权能', '可靠'),
       (145, '节期仪式', '马槽'),
       (146, '新生命', '像主效法转变改变更新'),
       (147, '新生命', '靠近主亲密亲近'),
       (148, '神的护理和诫命', '坚固'),
       (149, '神的国', '奥秘'),
       (150, '颂赞', '大能全能'),
       (151, '礼拜/宗教改革', '教会生活'),
       (152, '圣经经文', '希伯来书'),
       (153, '圣经经文', '旧约'),
       (154, '新生命', '重生'),
       (155, '神的护理和诫命', '圣灵的工作'),
       (156, '礼拜/宗教改革', '传道听道'),
       (157, '节期仪式', '新年'),
       (158, '节期仪式', '玛利亚'),
       (159, '颂赞', '公义'),
       (160, '新生命', '坚韧坚忍持守守望'),
       (161, '礼拜/宗教改革', '信条信经'),
       (162, '神的权能', '旨意'),
       (163, '神的权能', '宣告'),
       (164, '神的国', '新天新地'),
       (165, '神的护理和诫命', '奖赏'),
       (166, '礼拜/宗教改革', '称义'),
       (167, '神', '辩护者'),
       (168, '救赎', '流血牺牲舍命 '),
       (169, '神', '君王'),
       (170, '节期仪式', '加略山'),
       (171, '主题', '中国'),
       (172, '礼拜/宗教改革', '五个唯独'),
       (173, '颂赞', '至高伟大'),
       (174, '神', '圣父天父'),
       (175, '救赎', '羔羊宝血'),
       (176, '圣经经文', '阿门'),
       (177, '圣经经文', '爱的箴言'),
       (178, '新生命', '刚强壮胆'),
       (179, '神的护理和诫命', '律法'),
       (180, '新生命', '警醒'),
       (181, '新生命', '管家'),
       (182, '圣经经文', '主祷文'),
       (183, '神', '爱'),
       (184, '神', '真道圣道神的话'),
       (185, '情绪/节奏', '安慰安息'),
       (186, '比喻', '葡萄树'),
       (187, '比喻', '寓言');

--
-- 转储表的索引
--

--
-- 表的索引 `category_label`
--
ALTER TABLE `category_label`
    ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `category_label`
--
ALTER TABLE `category_label`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=188;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
