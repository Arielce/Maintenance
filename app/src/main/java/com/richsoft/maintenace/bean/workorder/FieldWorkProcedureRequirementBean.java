package com.richsoft.maintenace.bean.workorder;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：e430 on 2017/2/23 21:13
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class FieldWorkProcedureRequirementBean implements Serializable{
    /*type描述
    * 0-需指定是或者否
    * 1-数据记录
    * 2-拍照并记录图像编号
    * 3-开关柜数据记录
    * */
    public Integer type;
    public String title;//质量标准及要求标题
    public Integer verify;//如果type为0，该字段记录是与否
    public String data;//如果type为1，该字段记录数据
    public String dataUnit;//如果type为1，该字段记录数据单位
    public List<WorkPhoto> workPhotos;//如果type为2,记录照片路径（本地或者网络）
    public List<SwitchRoomBean> switchRooms;//如果type为3，该任务对应变电站的开关室集合
}
