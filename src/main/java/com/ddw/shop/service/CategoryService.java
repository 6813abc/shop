package com.ddw.shop.service;

import com.ddw.shop.dto.CategoryDto;
import com.ddw.shop.dto.GoodDto;
import com.ddw.shop.entity.Good;
import com.ddw.shop.exception.BaseResult;
import com.ddw.shop.exception.ResultEnum;
import com.ddw.shop.exception.ResultUtil;
import com.ddw.shop.mapper.CategoryMapper;
import com.ddw.shop.mapper.CategoryMybatisMapper;
import com.ddw.shop.mapper.GoodMapper;
import com.ddw.shop.mapper.GoodMybatisMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ddw
 * @date 2020/3/17 14:06
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryMybatisMapper categoryMybatisMapper;
    @Autowired
    GoodMapper goodMapper;
    @Autowired
    GoodMybatisMapper copyProperties;
    private Integer length = 24;

    public List<CategoryDto> getCategory() {
        List<CategoryDto> categoryDtos = categoryMybatisMapper.findByParentId(0L, "/");
        for (CategoryDto categoryDto : categoryDtos) {
            List<CategoryDto> categoryChildes = getCategory(categoryDto.getCid());
            categoryDto.setCategoryDtos(categoryChildes);
        }
        return categoryDtos;
    }

    public List<CategoryDto> getCategory(Long id) {
        List<CategoryDto> categoryChildes = categoryMybatisMapper.findByParentId(id, "/");
        categoryChildes = categoryChildes.size() > length / 3 ? categoryChildes.subList(0, length / 3) : categoryChildes;
        if (categoryChildes.size() == 0) {
            //返回空list，结束递归，下次递归就不会进入for循环
            return new ArrayList<>();
        }
        //暂时就查询一层，不继续递归
        /*for (CategoryDto categoryDto : categoryChildes) {
            categoryDto.setCategoryDtos(getCategory(categoryDto.getCid()));
        }*/
        return categoryChildes;
    }

    public Map getMap() {
        Map<String, String[]> map = new LinkedHashMap<>();

        map.put("女装", new String[]{"连衣裙", "半身裙", "毛针织衫", "T恤", "短外套", "卫衣", "汉服", "衬衫", "牛仔裤", "裤裙", "西装", "时尚套装", "风衣", "休闲裤", "马甲", "阔腿裤", "打底裤", "旗袍", "吊带", "雪纺衫"});
        map.put("男装", new String[]{"T恤", "衬衫", "POLO衫", "休闲裤", "牛仔裤", "套装", "外套", "夹克", "卫衣", "风衣", "西装", "牛仔外套", "棒球服", "皮衣", "运动裤", "工装裤", "马甲", "情侣装", "羽绒服", "棉衣"});
        map.put("内衣", new String[]{"法式内衣", "内裤女", "文胸", "内裤男", "长袖睡衣", "背心", "睡裙", "真丝睡衣", "丝袜", "睡袍", "塑身衣", "连体睡衣", "打底裤", "卡通睡衣", "男士袜子", "卡通睡衣"});

        map.put("鞋靴", new String[]{"流行女鞋", "单鞋", "靴子", "运动鞋", "高跟鞋", "厚底鞋", "内增高", "蝴蝶鞋鞋", "玛丽珍鞋", "豆豆鞋", "小白鞋", "一脚蹬", "圆头鞋", "男鞋", "休闲鞋", "板鞋", "帆布鞋", "船鞋", "增高鞋", "尖头鞋"});
        map.put("箱包", new String[]{"女包", "双肩包", "男包", "旅行箱", "钱包", "真皮包", "大牌", "宽肩带", "小方包", "水桶包", "迷你包", "链条包", "贝壳包", "波士顿包", "手拿包", "单肩包", "手提包", "零钱包", "斜挎包", "拉杆箱"});
        map.put("配件", new String[]{"帽子", "贝雷帽", "渔夫帽", "鸭舌帽", "礼帽", "草帽", "爵士帽", "盆帽", "八角帽", "丝巾", "披肩", "真丝围巾", "方巾", "手套", "真皮手套", "触屏手套", "半指手套", "腰带", "手工皮带", "真皮腰带"});

        map.put("家电", new String[]{"生活电器", "厨房电器", "个人护理", "空气净化器", "扫地机器人", "吸尘器", "取暖器", "剃须刀", "烤箱", "豆浆机", "电饭煲", "吹风机", "剃须刀", "卷发器", "电暖桌", "加湿器", "暖风机", "蓝牙音箱", "电热毯"});
        map.put("数码", new String[]{"游戏主机", "手机壳套", "数码精选", "苹果手机壳", "电脑主机", "数码相机", "单发相机", "游戏主机", "鼠标键盘", "无人机", "二手手机", "二手平板电脑", "二手笔记本"});
        map.put("手机", new String[]{"iPhone xs", "华为Mate20P", "小米MIX3", "荣耀Magic2", "一加6T", "小米", "魅族", "华为P20", "vivo", "OPPO", "黑鲨2代", "努比亚X", "iPhone X", "iPhone 8"});

        map.put("美食", new String[]{"牛奶", "柚子茶", "酸梅汤", "矿泉水", "酵素 ", "藕粉", "大米", "小米", "黄豆", "火腿", "香肠", "木耳", "枸杞", "人参", "石斛", "雪蛤", "绿茶", "蜂蜜", "天麻花粉", "铁观音"});
        map.put("生鲜", new String[]{"荔枝", "水果", "百香果", "芒果", "小龙虾", "樱桃", "榴莲", "杨梅", "牛排", "柠檬", "海参", "水蜜桃", "咸鸭蛋", "李子", "桃子", "龙虾", "苹果", "黄桃", "火龙果", "菠萝蜜"});
        map.put("零食", new String[]{"零食大礼包", "牛肉干", "面包", "辣条", "红枣", "核桃", "饼干", "巧克力", "葡萄干", "芒果干", "绿豆糕", "薯片", "锅巴", "海苔", "月饼", "蛋黄酥", "猪肉脯", "花生", "瓜子", "棒棒糖"});

        map.put("眼镜", new String[]{"眼镜架", "3D眼镜", "司机镜", "防辐射眼镜", "老花镜", "儿童镜", "色盲眼镜", "无框眼镜", "眼镜片", "雷朋", "复古眼镜", "超轻眼镜", "滑雪镜", "超耐磨", "护目镜", "眼镜配件"});
        map.put("手表", new String[]{"运动表", "卡西欧", "国表", "时尚表", "女表", "儿童表", "学生表", "浪琴", "瑞士表", "表带", "休闲", "复古手表", "中性手表", "情侣表", "陶瓷表", "怀表", "钢带表"});
        map.put("珠宝", new String[]{"钻戒", "铂金", "黄金首饰", "高端定制", "彩色宝石", "珍珠", "钻石", "翡翠玉石", "一元起拍", "设计师", "珠宝首饰", "金条", "情侣对戒", "琥珀原石", "裸石", "金镶玉"});

        map.put("运动", new String[]{"Yeezy 350", "Alpha Bounce", "Aj30", "Stan Smith", "耐克", "阿迪达斯", "New Balance", "亚瑟士", "Under Amour", "匡威", "彪马", "VANS", "锐步", "斯凯奇", "李宁", "跑鞋", "篮球鞋", "足球", "羽毛球", "健身"});
        map.put("户外", new String[]{"鱼线", "鱼线轮", "登山包", "帐篷", "睡袋", "望远镜", "皮肤衣", "速干衣", "速干裤", "手电筒", "护具", "瑜伽", "跑步机", "健身器", "烧烤架", "冲锋衣", "遮阳帽", "单车零件", "户外手表", "战术鞋"});
        map.put("乐器", new String[]{"钢琴", "电钢琴", "电子琴", "萨克斯", "尤克里里", "架子鼓", "小提琴", "口琴", "二胡", "葫芦丝", "陶笛", "琵琶", "笛子", "非洲鼓", "贝斯", "调音器", "节拍器", "电吉他", "电子鼓", "手风琴"});

        map.put("美妆", new String[]{"面膜", "洁面", "防嗮", "爽肤水", "眼霜", "乳液", "面霜", "精华", "卸妆", "男士护肤", "眼线", "粉底液", "BB霜", "隔离", "睫毛膏", "唇膏", "香水", "精油", "腮红", "彩妆盘"});
        map.put("洗护", new String[]{"洗发水", "护发素", "发膜", "染发膏", "假发", "烫发水", "沐浴露", "身体乳液", "牙膏", "牙刷", "漱口水", "足浴", "足贴", "洗手液", "卫生巾", "抽纸", "卷纸", "洗衣液", "清洁剂", "香薰"});
        map.put("保健品", new String[]{"B族维生素", "葡萄籽", "消化酶", "软骨素", "维生素C", "钙片", "益生菌", "鱼油", "生物素", "玛咖", "酶素", "螺旋藻", "胶原蛋白", "DHA", "蔓越莓", "褪黑素", "左旋肉碱", "月见草油"});

        map.put("家具", new String[]{"沙发", "床", "高低床", "餐桌", "床垫", "茶几", "电视柜", "衣柜", "鞋柜", "椅凳", "书桌", "电脑桌", "坐具", "真皮沙发", "皮床", "实木床", "花架", "书架", "儿童学习桌", "电脑椅"});
        map.put("家饰", new String[]{"窗帘", "地毯", "沙发垫", "十字绣", "桌布", "地垫", "抱枕", "坐垫", "飘窗垫", "门帘", "缝纫机", "卷帘", "珠帘", "沙发巾", "靠垫", "摆件", "照片墙", "相框", "墙贴", "花瓶"});
        map.put("家纺", new String[]{"夏凉被", "草席", "床褥", "U型枕", "蚊帐", "凉席", "天丝套件", "提花套件", "婚庆套件", "儿童套件", "空调被", "四件套", "毛巾被", "记忆枕", "枕头", "竹席", "老粗布", "婚庆床品", "冰丝席", "宫廷蚊帐"});

        map.put("办公", new String[]{"打印机", "一体机", "复合机", "3D打印机", "投影机", "收银机", "收银纸", "电子面单机", "保险箱", "会议白板", "安防摄像", "无限网卡", "WIFI放大器", "无限呼叫器", "理线器", "计时器", "马克笔", "翻页笔", "碎纸机", "画具画材"});
        map.put("DIY", new String[]{"定制T恤", "文化衫", "工作服", "卫衣定制", "LOGO设计", "VI设计", "海报定制", "3D效果图制作", "广告扇", "水晶奖杯", "胸牌工牌", "奖杯", "徽章", "洗照片", "照片冲印", "相册", "纸箱", "胶带", "二维码贴纸", "塑料袋"});
        map.put("学习", new String[]{"英语四级", "考研", "成人学历", "小学教学", "雅思托福", "学历提升", "会计提升", "ps美工技能", "考研辅导", "外教口语课", "建造师", "驾照报名", "汽车维修", "公务员考试", "宝宝早教", "健身减肥", "DIY手工", "微信小程序", "JAVA", "CAD教程"});

        return map;
    }

    public List<GoodDto> getGoodByCId(Long cid, Long page, Integer limit) {
        Long index = (page - 1) * limit;
        return copyProperties.findByCid(cid, index, limit);
    }
}
