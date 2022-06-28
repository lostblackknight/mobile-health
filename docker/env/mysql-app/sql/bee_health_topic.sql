/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : bee_health_topic

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 18/06/2022 13:49:06
*/
CREATE DATABASE IF NOT EXISTS bee_health_topic  DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;
USE bee_health_topic;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者名称',
  `uid` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面',
  `category_id` bigint(255) NULL DEFAULT NULL COMMENT '分类ID',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章内容',
  `read_count` bigint(255) NULL DEFAULT 0 COMMENT '阅读数量',
  `like_count` bigint(255) NULL DEFAULT 0 COMMENT '点赞数量',
  `collection_count` bigint(255) NULL DEFAULT 0 COMMENT '收藏数量',
  `is_deleted` tinyint(255) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES (1, '健康减肥晚餐怎么吃？一周快手带量食谱示范', '王波', 2, 'https://pic.wenwo.com/fimg/84392148211.jpg', 1, '<pre>一直有朋友催晚餐食谱，谷老师终于给大家搭配、制作好了。<br /><br />先来说说减肥晚餐的搭配原则。《中国营养科学全书》建议，减肥时晚餐以清淡为主，三餐能量比午餐早餐晚餐，我在这两个原则的基础上，还搭配了充足的蔬菜和蛋白，主食也做到了粗细搭配。<br /><br />一、口味清淡<br />  1、肉类选择脂肪含量较低的瘦猪肉、瘦牛肉、鸡胸脯肉、鱼、虾、贝。<br />  2、为了控制油的用量，炒菜和煎鸡胸、青花鱼都用了不粘锅，沙拉用了低脂沙拉酱，凉拌菜加了坚果碎就没加油，清炖汤也放油很少。<br />  3、菜里都没加糖，加了生抽的菜就没再加盐。<br /><br />二、能量适宜对于没啥活动量的女生，要饥饿感不明显的减肥，每天大概摄入1500千卡能量较为适宜。按照午餐能量早餐能量晚餐能量的原则，我们可以将早、中、晚餐的供能比设定在35%：40%：25%，即晚餐能量为375千卡左右。<br /><br />三、主食粗细搭配，蔬菜和蛋白充足这样搭配是因为有研究显示，相比于早餐，晚餐的餐后血糖更难控制。而血糖控制不好，更容易转化成脂肪堆积起来，容易胖人，长此以往也更易发展成糖尿病。粗粮和蔬菜富含膳食纤维，可以延缓葡萄糖吸收入血，从而有利于控血糖，而富含蛋白的食物不仅血糖生成指数(GI)低，还能延长饱腹感，利于减肥。7天的减肥晚餐具体怎么搭配的，有哪些食材选购、互换技巧，一起来看吧。<br /><br />第一天晚餐(375千卡)<br />  <img src=\"https://pic.wenwo.com/fimg/76f0c09e3daea07c9ba518cc48f1275d.jpg\" alt=\"\" /><br />  藜麦饭：大米15克、藜麦35克西红柿菌菇虾仁汤：西红柿200克、青虾仁50克、北豆腐100克、香菇50克、橄榄油10克食材互换及营养贴士：<br />  1、藜麦可以换成糙米、紫米、燕麦米、绿豆、红豆、鹰嘴豆等杂粮杂豆，50克米相当于做小蛋糕的杯子半杯。<br />  2、一个稍大个头的西红柿约200克，为了节约时间，西红柿也可以互换成西红柿块的罐头，不过要配料只有西红柿的，无添加糖。<br />  3、7个青虾仁约50克，也可以替换成50克巴沙鱼，都是低脂的优质蛋白。<br />  4、北豆腐钙含量很丰富，100克大概是5块麻将大小。<br />  5、3小朵香菇约50克，可替换成金针菇、海鲜菇、杏鲍菇。<br />  6、橄榄油比葵花籽油、玉米油、大豆油更稳定，更适合炒菜，有朋友担心加热会损失其中的活性成分，其实不用担心，因为油99%的成分都是脂肪，活性成分真的很低。<br /><br />第二天晚餐(370千卡)<br />  <img src=\"https://pic.wenwo.com/fimg/8f29b3d433e544cc49ebccc27c544c3f.jpg\" alt=\"\" /><br />  玉米棒子1根300克青椒炒鸡胸脯肉：鸡胸脯肉50克、青椒100克、花生油7克小圣女果50颗、无糖酸奶100克食材互换及营养贴士：<br />  1、减肥建议选能量较低的甜玉米，一根玉米约300-400克，可以替换130克米饭作为一餐主食。但是不要顿顿吃玉米，毕竟它的蛋白含量不及米面，其中的烟酸属于结合型，不能被人体吸收，虽然加碱煮可以转化成游离型烟酸，可是会破坏我们更易缺乏的维生素B1和维生素B2，所以煮玉米时别加碱。<br />  2、青椒维生素C含量为59毫克/100克，100克青椒就能满足人体每日维生素C需求的59%。不过它口感较辣，吃不了辣的朋友可以替换成彩椒，彩椒维生素C含量更丰富(104毫克/100克)。<br />  3、50克鸡胸脯肉大概有3个麻将大小，可以替换成等量牛里脊肉。<br /><br />第三天晚餐(372千卡)<br />  <img src=\"https://pic.wenwo.com/fimg/8d9defa1e7ae7db9375a391f57d43016.jpg\" alt=\"\" /><br />  紫米馒头70克香煎鸡胸肉50克小白菜拌豆腐汤：白菜200克、豆腐100克、7克山核桃、橄榄油3克、亚麻籽油2克食材互换及营养贴士：<br />  1、紫米馒头可以替换成等量玉米窝窝头、全麦面包。<br />  2、鸡胸肉煎之前用少许淀粉、料酒和奥尔良烤鸡翅粉腌制10分钟，煎出来的鸡肉更嫩。<br />  3、5颗小白菜约200克，小白菜需要沸水焯30秒捞出过凉再拌豆腐，焯水可以去除影响钙吸收的草酸。小白菜也可以替换成菠菜。<br /><br />第四天晚餐(377千卡)<br />  <img src=\"https://pic.wenwo.com/fimg/40b11ed9e51a34bb72312a0bd7f0cc42.jpg\" alt=\"\" /><br />  蒸南瓜100克蒸土豆100克芹菜炒肉丝：芹菜200克、猪瘦肉40克、花生油6克脱脂牛奶200毫升食材互换及营养贴士：<br />  1、蒸南瓜的南瓜是贝贝南瓜，它的能量、碳水含量都和土豆相当，可以替代部分主食，南瓜、土豆，还可以替换成铁棍山药、芋头。中国居民膳食指南建议薯类每天吃50-100克，不是天天吃就可以加倍吃，但是不能经常用薯类完全替代主食，因为它的蛋白含量比米面要低。<br />  2、芹菜可以替换成莴笋、秋葵。<br />  3、40克瘦猪肉大概相当于2.5块麻将大小，瘦猪肉跟其它瘦肉比，很补维生素B1。<br /><br />第五天晚餐(377千卡)<br />  <img src=\"https://pic.wenwo.com/fimg/e47a98e807c576a4b1a136ffb08c489c.jpg\" alt=\"\" /><br />  全麦面包70克香煎青花鱼75克蔬菜沙拉：生菜100克、小圣女果50克、4克扁桃仁、沙拉酱10克、油1克食材互换及营养贴士：<br />  1、70克全麦面包可以替换成50克燕麦片，或70克杂粮馒头，选全麦面包时建议选全麦粉含量在50%以上，少糖或无糖。<br />  2、青花鱼比三文鱼还补DHA，却比三文鱼便宜，煎之前用厨房纸擦干水分，打花刀撒点混合盐腌制10分钟就行，它脂肪含量高，煎的时候不粘锅里刷不刷油都行，出锅时淋点柠檬汁，味道更好。<br />  3、为了控制能量和脂肪摄入，建议减肥时尽量选低脂的沙拉酱。<br /><br />第六天晚餐(372千卡)<br />  <img src=\"https://pic.wenwo.com/fimg/550579378f57c0c58dee4337ab4e1ccc.jpg\" alt=\"\" /><br />  杂粮饭：大米12.5克、紫米12.5克、燕麦25克海带丸子汤：牛肉丸75克、海带100克黄瓜100克食材互换及营养贴士：<br />  1、为了节约时间，可以周末做多点儿杂粮饭，分装冷冻起来，做为1顿主食最少吃130克，吃得时候微波加热一下就行。<br />  2、海带一定得多泡几遍，太咸了，另外煮汤时不用再加盐。如果泡好几遍还咸，就多加水煮汤。<br />  3、肉丸最好选肉的含量在90%以上的。<br />  4、黄瓜可以替换成生菜、西红柿、小圣女果等方便直接吃的蔬菜。<br /><br />第七天晚餐(371千卡)<br />  <img src=\"https://pic.wenwo.com/fimg/4b0c0a490626e0aae4bdd6bc9f317de9.jpg\" alt=\"\" /><br />  脱脂牛奶冲燕麦片50克、脱脂牛奶200毫升蒜苗炒蛏子：蛏子75克、蒜苗100克、花生油5克100克樱桃萝卜食材互换及营养贴士：<br />  1、燕麦片尽量选压片完整一些的，升血糖更慢。<br />  2、蛏子的铁和锌含量很丰富，75克蛏子相当于带壳132克，10个蛏子，达到了中国居民膳食指南推荐的水产品(40-75克/天)的量。蛏子也可以替换成蛤蜊，也是低脂优质蛋白，不过补铁略差一点儿。蛏子需要沸水焯1分钟，焯水时加点料酒去腥，出锅过凉去壳再炒。这就是谷老师给大家编制的7天减肥晚餐，适合没啥活动的大多数女性朋友。如果你这么吃睡觉前就饿了，那就适当增加一点儿主食。细嚼慢咽的吃，先吃菜和蛋白后吃主食，大概6-8点吃饭，睡前不饿是晚餐吃的量是否合适的标准哦。</pre>', 0, 0, 0, 0, '2022-04-24 00:14:33', '2022-04-30 17:54:32', 'doctor');
INSERT INTO `t_article` VALUES (2, '怎么治疗尖锐湿疣不复发', '王波', 2, 'https://pic.wenwo.com/fimg/68985145271.jpg', 2, '<pre>尖锐湿疣复发是患者最烦恼、最头疼的事情，一旦尖锐湿疣复发，患者都会遭受尖锐湿疣痛苦的折磨，尖锐湿疣复发主要是因患者的采用的治疗方法不当，没有做好预防措施，那么究竟怎么治疗尖锐湿疣不复发呢，这是很多患者都想知道的问题，针对这一问题，下面就一起来具体了解一下吧。<br /><br />冷冻疗法治疗尖锐湿疣，利用-196℃低温的液体氮，用低温的办法来治疗，从而促进疣组织坏死脱落，但是该方法有一定的局限性，只适用于那些数量少，面积小的湿疣患者，治疗时隔一周一次，可进行一到两次的治疗。<br /><br />采用微波手术治疗机来治疗尖锐湿疣，采用局麻，治疗时将杆状辐射探头尖端插入尖锐湿直达疣体基底，当看到病体变小、颜色变暗、由软变硬的时候，那么热辐射凝固就算完成了，此时可将探头抽出。然后用镊子将凝固的病灶取出来。如果是为了防止复发，可对残存的基底在进行凝固。<br /><br />全身治疗，这种治疗方法适用于顽固难治、反复发作或年老体弱的患者。对于该类患者的治疗，可选用基因工程干扰素&alpha;-2a或&alpha;-2b来对病灶进行治疗，治疗周期大约为每周3次，具体根据患者病情来定。<br /><br />手术疗法治疗尖锐湿疣一般是针对那些单发、面积小的湿疣，可以采用手术切除的办法，如果是病情严重的患者，还要根据医生的建议来进行治疗。<br /><br />相信大家在看完上述的介绍后，应该是对尖锐湿疣这种疾病的治疗办法有了更加深一层的认识。<br /><br />尖锐湿疣是一种比较麻烦的疾病，具有传染性而且还容易复发，如果不能治好的话，可能会影响生育。</pre>', 0, 0, 0, 0, '2022-04-24 00:25:05', '2022-04-30 17:55:06', 'doctor');
INSERT INTO `t_article` VALUES (3, '尖锐湿疣带来的后果有哪些', '王波', 2, 'https://pic.wenwo.com/fimg/61480145260.jpg', 4, '<pre>受传统思想的影响，大部分人们都认为患有性病是非常可耻的事情，尖锐湿疣，有的是菜花状，有的是扁平的，甚至有些是肉眼看不见的，这样的情况叫亚临床感染，可以通过醋酸白的方法检验出来，看有没有亚临床的感染。<br /><br />另外还有是生殖器部位的感染，是看不见的，是隐性感染，这种也是比较常见的。　　尖锐湿疣是一种由病毒的感染所造成的性传播的疾病。如果我们没有能及时的治疗这样就会给患者们带来很严重的危害，更严重的还是可能会导致患者的死亡的。<br /><br />下面就来说说尖锐湿疣的危害有哪些：　　<br /><br />危害一：<br />   尖锐湿疣它对自己的危害，淋病与非淋菌性的尿道炎如果治疗不及时它就会导致腹膜炎和盆腔的脓肿。生殖器的疱疹有的时候还会让自身的尿道变的狭窄。梅毒它不仅是侵犯皮肤粘膜的，它还侵犯全身的脏器，像是心血管的梅毒它就会导致主动脉炎，神经梅毒它会导致脊髓的痨以及进行性的麻痹痴呆。<br /><br />危害二：<br />  尖锐湿疣也是会影响家庭的其他人的，夫妻双方只要有一方患尖锐湿疣，另一方被传染患病的几率会是很高的。另外也可能会通过日常生活中密切生活的接触传染给家属。所以，家庭中如有性病患者就应该注意消毒与隔离，就算没有明显的症状，他家庭成员也应该到正规的医院去检查。<br /><br />危害三：<br />  尖锐湿疣对生育会有影响，梅毒或非淋菌性尿道炎的病原体是可通过胎盘传给胎儿的，会导致孕妇的流产、早产、胚胎死亡、先天畸形，沙眼衣原体可导致胎儿宫内发育的迟缓。自发性流产主要是与尖锐湿疣、淋病、支原体或单疱病毒有关的。淋病可造成有缺陷新生儿出生，还能使新生儿患有眼炎最好导致失明。</pre>', 0, 0, 0, 0, '2022-04-24 00:29:05', '2022-04-30 19:55:59', 'doctor');
INSERT INTO `t_article` VALUES (4, '做菜先焯水，百病不近身，其实肉类菜类各不相同，你做对了吗', '王波', 2, 'https://pic.wenwo.com/fimg/healthDefaultImgs.jpg', 3, '<pre>崔存青一级营养师慢病营养康复导师家里常做饭的人都有这样的体验，有些蔬菜、肉类焯一下水再烹饪，口感会变得更加柔嫩多汁，而且可以少放一些调料来掩盖食材的腥味。<br /><br />站在营养学的角度上来说，食材焯水不仅会让口感变得更好，还会增加饮食的安全性。一些绿叶蔬菜中含有大量的草酸、单宁等成分，让蔬菜充满草腥味和苦涩味，而且对人体健康也没有益处，过多的草酸会和身体中的钙离子等金属离子结合形成结石。<br /><br />而肉类焯水可以除去肉类中残留的血，还有肉的膻味和腥味，在流行性病毒仍然笼罩全球的背景下，进口冰鲜食材具有很大的安全风险，但是焯水可以有效地杀灭病毒和微生物，为我们的饮食安全保驾护航。<br /><br />但焯水时间对食材的口感影响很大，根据食材的种类和质地不同，焯水的方法也是有区别的，你知道其中的区别吗？<br /><br />对于肉类，比如牛羊肉和牛百叶、牛肚等内脏食材，需要冷水下锅，原料与冷水同时下锅，让水没过原料，这种焯水方法针对荤类食材更为合适。这样可以帮助我们更好地去除食材里的血污及腥膻异味，让肉类更加鲜嫩。<br /><br />如果沸水下锅，就会发生食材内部不热，外部过热的问题，看起来好像已经焯熟了，但其实内部的血污还没清除干净，如果继续焯烫直到内部也熟透，肉制会迅速变老发柴，不好吃了。对于颜色鲜艳、质地比较薄的蔬菜，开水下锅更为合适。<br /><br />等锅内的水加热到沸腾后再将原料下锅，可以有效减少蔬菜中的草酸，还能保留蔬菜鲜艳的色泽，建议大家下锅后及时翻动蔬菜，时间不要太长，否则也会过于软烂。对于竹笋、萝卜、等体积比较大，质地比较厚实的蔬菜原料，也是冷水入锅更合适，与肉类一样，让热量充分进入到食材内部，达到内外温度均衡，口感才会更好。<br /><br />总之，建议大家在流行病毒还没消散之前，最好不要吃生鲜的食物，对于一些草酸含量比较高的蔬菜，比如菠菜，一定要完全烫熟或炒熟后再吃。</pre>', 0, 0, 0, 0, '2022-04-24 00:31:43', '2022-04-30 17:55:19', 'doctor');
INSERT INTO `t_article` VALUES (5, '尿道口尖锐湿疣怎么办？是不是很严重', '王波', 2, 'https://pic.wenwo.com/fimg/40497144902.jpg', 4, '<pre>尿道口尖锐湿疣是相对比较严重的性传播疾病，在面对于它的存在时，是需要通过全面性对于身体内的人乳头病毒有效去除之后，再认真对身体进行有效的锻炼，最终才可以让尿道口尖锐湿疣的情况得到妥善的治愈效果。<br /><br />尖锐湿疣是由人乳头病毒而引起的病毒性，而在面对于尖锐湿疣这个存在时，自身方面是属于性传播疾病，所以为了能够更好地将尖锐湿疣全面性的治疗，是需要花费很大的时间和精力，并且在面对于它的出现时，自身方面是会存在很大的损害性现象，并且在没有特别注意下，是可以通过与他人性接触后而引起对他人进行传染。<br /><br />尿道口尖锐湿疣是很严重的疾病，一方面它是属于病毒性方面的传染疾病，而且它是可以通过性生活而进行有效的传播，如果是处理不当时，是会在进行性生活这个过程中而引起他人的身体也同时出现问题，所以面对于尿道口尖锐湿疣这个问题是需要认真进行治疗。　　<br /><br />另一方面是存在很大的传染性病变几率，所以在面对于尿道口尖锐湿疣这个疾病时，它肯定是属于严重的。毕竟它的传染性那么地强，一旦是处理不当的话，就会使尿道口处以及周边的位置全部生长出湿疣的病变现象。</pre>', 0, 0, 0, 0, '2022-04-24 00:33:25', '2022-04-30 17:55:25', 'doctor');
INSERT INTO `t_article` VALUES (6, '肾病综合征，除了激素还有什么药降尿蛋白', '张丽', 3, 'https://pic.wenwo.com/fimg/healthDefaultImgs.jpg', 2, '<pre>肾病综合征是肾内科常见的症状，只要同时符合两个条件就诊断为肾病综合征：<br />  1、大量蛋白尿，尿蛋白定量&ge;3.5g/L；<br />  2、低蛋白血症，血浆白蛋白&le;30g/L。<br /><br />许多疾病都可能出现肾病综合征，比如糖尿病、肾小球肾炎、紫癜性肾炎、狼疮、系统性小血管炎等，一般来说，除了糖尿病，其他疾病导致的肾病综合征都可能要使用激素。<br />除了激素外，都可以使用沙坦类降压药、普利类降压药和列净类降糖药，这三类药在各自降压、降糖的基础上，都有降尿蛋白和保肾作用。<br /><br />根据最新发布的指南，即使没有高血压和糖尿病，肾病综合征都可以使用。其中，沙坦类或普利类降压药可以和列净类降糖药联合使用。<br />此外，肾病综合征低蛋白的原因是尿中丢失大量蛋白，如果不&ldquo;堵漏&rdquo;而盲目补充蛋白的话，结果会越补漏蛋白越多。所以还要适量控制蛋白摄入。\"</pre>', 0, 0, 0, 0, '2022-04-24 00:36:03', '2022-04-30 17:55:32', 'doctor');
INSERT INTO `t_article` VALUES (7, '红米高纤补血健脾胃', '张丽', 3, 'https://pic.wenwo.com/fimg/51173148185.jpg', 1, '<pre>郎中天下伊美桃花源学院米饭小知识红米的红色来自天然花青素浸水后稍为脱色属正常情况红米纤维多记得饭后多喝水红米高纤补血健脾胃想吃得健康，一些人煮饭时都习惯性地在白米饭中混入适量红米烹煮，增加摄入膳食纤维。<br /><br />曾有传言指食用红米会令肝酵素上升甚至致癌，其实是网络传言将红米与红麴米混淆了，红麴米并非米的一种，而是大米加入红麴菌后发酵的产品，不会作为主食。<br />而红米是糙米的一种，所以不要误会无辜的红米了，它既高纤又有补血功效，脾胃功能正常的话不妨多吃。<br /><br />红米&mdash;糙米的一种，由于留有较多未磨走的表壳，更有营养，和白米的补气养脾胃功效相当，适合有糖尿、贫血、肥胖和便秘的人适量进食。<br />红麴米&mdash;又名红曲米，是一种将红麴菌接种在蒸熟大米上发酵加工的米，呈紫红或棕红色，一般磨成粉后用于发酵、防腐及食物上色等功能上，例如制作红腐乳及味噌。<br /><br />问：感冒或经期时候可以饮米水吗？<br />答：米水属性平和，感冒和经期时候均可饮用，米水尤其适合腹泻患者饮用，因为白米有固肠止泻功效。\"</pre>', 0, 0, 0, 0, '2022-04-24 00:38:26', '2022-04-30 17:55:37', 'doctor');
INSERT INTO `t_article` VALUES (8, '多数胰腺癌为什么不能手术切除？', '张丽', 3, 'https://pic.wenwo.com/fimg/68910148175.jpg', 3, '<pre>南医大二附院&ldquo;胰腺生机&rdquo;胰腺癌云门诊，4月27日（周三）下午15:00在好大夫诊室进行，钱祝银主任团队解析《多数胰腺癌为什么不能手术切除？》，通过线上解决患者诊疗疑问，帮助胰腺癌患者快速、及时的就医，把握最佳治疗时机，为胰腺癌患者开通&ldquo;生命通道&rdquo;。<br /><br />本期预告　　<br /><br />同一个胰腺癌患者，为何有些医生说能切？有些医生说不能？　　<br />胰腺癌手术切除、化疗、冷热复合消融术治疗怎么选？　　<br />胰腺癌不同分期如何选对治疗方案？　　<br />胰腺癌警惕五大症状，&ldquo;癌中之王&rdquo;可诊可治；　　<br />&ldquo;胰腺生机&rdquo;云门诊:胰腺癌患者的&ldquo;生命通道&rdquo;，如何快速获得精准诊断及治疗帮助<br />哪些患者可以申请云门诊？<br />　　<br />疑似胰腺癌，确诊为胰腺癌想要了解治疗方法，没有根治手术机会的局部进展期（Ⅲ期）胰腺癌；<br />有肝脏寡转移的晚期（Ⅳ期）胰腺癌；<br />局部中晚或转移性胰腺神经内分泌肿瘤；<br />已经做过胰头或胰腺体尾部切除的胰腺癌患者；<br />其他部位来源的肿瘤直接侵犯或转移至胰腺，无法行根治切除，但无远处转移者；<br />影像学评估为可切除胰腺肿瘤，但患者不愿意手术或年龄大，全身情况差无法耐受根治性切除手术者；<br /><br />不可手术切除及根治术预后较差的胰腺钩突癌患者，均可报名参加！<br />　　<br />讲座及义诊时间：2022年4月27日下午15:00　　<br />提醒：报名成功后，医生助理会协助患者整理本次问诊所有的病情资料。<br />　　<br />&ldquo;癌中之王&rdquo;--胰腺癌并非不可战胜　　<br />钱祝银主任带领的胰腺中心打破传统诊疗模式，结合了消化外科、消化内科、消化内镜中心、影像科、放疗科、内分泌科、介入诊疗科、病理科、麻醉科等多学科的优势力量，多学科综合治疗手段开创了以疾病为中心的全新诊疗模式。实现对不同病期胰腺肿瘤的全面覆盖，精准打击。<br />针对早期患者，我们通过根治手术切除，对于处于中晚期无法手术切除的患者，钱祝银主任团队首创的冷热复合消融治疗技术，给这部分患者带来生机。<br />&ldquo;中晚期不可手术切除的胰腺癌患者，传统治疗是化疗、姑息治疗等，对于原发病灶并没有处理，门诊经常遇到这部分患者，经过多次的化疗、姑息治疗，最后把身体折腾垮了，肿瘤却转移了。<br />但是如果能先进行冷热复合消融手术治疗，将肿瘤的有生力量消灭掉，再结合化疗，这样的治疗方案比单纯化疗效果要好。术后的患者疼痛消失了、黄疸没有了，饮食正常了，生活恢复正常。&rdquo;钱祝银主任介绍。<br />首个&ldquo;胰腺生机&rdquo;--云门诊：为胰腺癌患者开通&ldquo;生命通道&rdquo;，快速获得精准诊断及治疗帮助！<br />为了尽可能的帮助患者解决诊疗难题，为胰腺癌患者的治疗争取宝贵的救命时间。南医大二附院胰腺中心开启了首个胰腺癌云门诊，钱祝银主任建立了胰腺癌&ldquo;一站式、多学科全新诊疗模式&rdquo;。每周三下午15:00&ldquo;胰腺生机&rdquo;--云门诊，通过线上第一时间就能与专家连线问诊，与线下就诊无缝对接，运用冷热复合消融术为不可切除中晚期胰腺癌患者提供精准、有效、个体化治疗方案。<br />同时中心通过线上讲座、科普宣教、典型病例专家全方面解析，将胰腺癌科学、准确的防治知识传递给公众，医患共同携手，为推进&ldquo;健康中国&rdquo;建设尽我们的力量。\"</pre>', 0, 0, 0, 0, '2022-04-24 00:42:35', '2022-04-30 17:55:43', 'doctor');
INSERT INTO `t_article` VALUES (9, '滑膜炎可以治愈吗？该如何治疗？', '张丽', 3, 'https://pic.wenwo.com/fimg/healthDefaultImgs.jpg', 2, '<pre>\"据统计发现，我国现存滑膜炎患者约占总人口的50%。滑膜炎主要是由创伤、感染以及结核等因素引起，表现为关节肿胀、疼痛、关节腔有积液以及活动受限。那么滑膜炎可不可以治愈呢？<br />怎么治疗呢？下面小凤医生就带大家一起来了解一下。<br /><br />很多患者会问滑膜炎该如何治疗？可以痊愈吗？毫无疑问，大多数滑膜炎都是可以治愈的。<br />一般来说，我们所说的滑膜炎大多是指无菌性滑膜炎，对于这类患者用抗生素（青霉素、先锋头孢菌素、克林霉素等）治疗是无效的。<br />他们真正需要的是另一种非甾体抗炎药，这种药通常具有镇痛作用，是治疗过程中不可缺少的解决方案。因为它可以通过消除关节腔和滑膜的炎症，以达到镇痛作用，缓解关节肿胀。<br /><br />一、无菌滑膜炎治疗无菌性滑膜炎的治疗方法一般可以分为以下几种：<br />  （1）药物治疗：目前治疗药物主要为非甾体抗炎镇痛药，主要分为口服镇痛药和外用镇痛药。外用药物均为抗炎镇痛药，对慢性滑膜炎症或严重创伤急性期的治疗效果有限。口服药物与外用药物相似，可缓解疼痛等不适症状。<br />  （2）提取关节腔积液（不推荐）：主要目的是减轻关节腔压力，改善关节疼痛、屈伸活动有限等症状。适用于关节腔积液量大的患者（手术时必须严格执行无菌要求，防止关节感染）。对于关节腔积液，少量积液可自行吸收。<br />  （3）关节腔注射治疗：包括封闭针、玻璃酸钠注射液等。，通常与关节腔积液的抽吸治疗相结合。先尽量抽吸关节腔积液，然后将封闭针或玻璃酸钠注射液直接注入关节腔，再用弹性绷带适当加压包扎。<br />  （4）消除滑膜炎引起的刺激因素：对于急性损伤引起的滑膜炎，需要关节制动2-3周，避免关节感冒，避免急性运动，减少行走距离。儿童滑膜炎也能适当地吸引皮肤。（5）手术治疗：对于类风湿性、强制性脊柱炎引起的年轻滑膜炎患者，必要时可考虑切除滑膜，避免进一步侵犯关节软骨和骨组织。、对于老年人、骨增生、继发性骨关节炎或其他内科疾病（最常见的类风湿病）治疗不当引起的严重滑膜炎患者，除滑膜炎外，更重要的问题是关节软骨表面磨损或侵蚀损伤，因此简单的滑膜切除不能从根本上解决问题，此时优先进行全膝关节表面置换手术。<br /><br />二、如何预防滑膜炎<br />   01避免膝关节过度活动与劳累注意工作和休息的结合，适当的锻炼。长期、过度、剧烈的运动或活动是滑膜退化的基本原因之一，特别是对于持有重关节（如膝关节、髋关节），过度运动增加了关节表面的应力和磨损。适当的运动，特别是关节运动，可以增加关节腔内的压力，有利于关节液体渗入软骨，减少关节软骨的退行性变化，从而减少或预防滑膜炎。<br />   02控制体重肥胖者应适当控制饮食，注意调整饮食结构，减少热量摄入，控制体重，减轻关节压力和磨损，预防关节滑膜炎。<br />   03注意保暖，避免感冒注意调整衣服，保护膝关节容易发生滑膜炎，不要贪图空调，通常更注意锻炼，更好地预防滑膜炎，必要时穿护膝可以保暖，但也可以保护关节。<br />   04合理膳食避免过量摄入酸性物质，饮食中的酸碱平衡是滑膜炎治疗和并发症防治的一个非常重要的环节。老年人可以适当补充钙、维生素D和其他与骨代谢密切相关的药物，并进行适度的体育锻炼，以减缓骨组织的衰老和退行性变化。滑膜炎只要及时治疗还是可以治愈的，但是如果没有得到规范的治疗可能会使病情恶化，最后导致类风湿关节炎上门拜访。</pre>', 0, 0, 0, 0, '2022-04-24 00:45:31', '2022-04-30 17:55:48', 'doctor');
INSERT INTO `t_article` VALUES (10, '宫颈里长尖锐的人多吗', '张丽', 3, 'https://pic.wenwo.com/fimg/11644144769.jpg', 4, '<pre>有过不洁性接触的人是性病的高危群体，当然他们也可能会感染尖锐湿疣。引起尖锐湿疣的病原体是HPV病毒，这种病毒喜好温暖潮湿的部位，比如人体的外阴生殖器、肛周等部位，都是该病的好发区域。<br /><br />但是，这种病毒除了会侵犯人体体表的鳞状上皮以外，同样也可能感染到腔道部位，像男性的尿道、女性的宫颈等处也可能发生湿疣病变。<br /><br />那么，女性群体发生宫颈尖锐湿疣的人多吗？<br /><br />根据我院近20年的临床统计来看，女性患者发生在宫颈部位的占比还是比较大的。她们之所以会感染到宫颈部位，主要原因仍然是性接触的行为所致。<br />当男方生殖器部位患上了尖锐湿疣或者携带HPV病毒，他与女性发生关系时也并没有任何预防措施，也就是没有戴避孕套。在这种情况下，HPV病毒就很容易带入到女性的阴道壁、宫颈等处，导致感染，引起发病。<br />相信通过这样的描述，大家应该能明白为何会有很多女性尖锐湿疣长在了宫颈部位。既然得知其中原因，也就有相应的预防措施。<br /><br />比如，在发生关系时尽可能地戴避孕套。如果得知对方是尖锐湿疣患者或者HPV携带者，自然是不宜同房，避免增加感染风险。<br />如果女性朋友不慎查出来自己宫颈部位感染了尖锐湿疣，也千万不要担心，一般宫颈湿疣医院可能会采用物理方法来治疗。<br />常见的有利普刀、宫颈锥切术等，如果病情比较轻微的，也可能做过治疗就好了，但若出现反复发作，久治不愈的情况，就要更换医院和方法，尽量选择标本兼治的中医中药来进行除根，避免病情反复发作，否则容易出现久治不愈的情况。</pre>', 0, 0, 0, 0, '2022-04-24 00:48:06', '2022-04-30 17:55:53', 'doctor');

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `is_deleted` tinyint(255) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES (1, '1000', '食品健康', '食品健康', '2022-04-23 13:25:50', '2022-04-23 13:25:53', 0);
INSERT INTO `t_category` VALUES (2, '1001', '运动养生', '运动养生', '2022-04-23 13:26:27', '2022-04-23 13:26:29', 0);
INSERT INTO `t_category` VALUES (3, '1002', '体重控制', '体重控制', '2022-04-23 13:26:46', '2022-04-23 13:26:50', 0);
INSERT INTO `t_category` VALUES (4, '1003', '慢性疾病', '慢性疾病', '2022-04-23 13:27:40', '2022-04-23 13:27:43', 0);

SET FOREIGN_KEY_CHECKS = 1;
