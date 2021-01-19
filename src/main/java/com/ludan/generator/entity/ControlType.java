package com.ludan.generator.entity;

import java.util.Arrays;

/**
 * @author: chengchen
 * @create: 2021-01-18 20:32
 **/
public enum ControlType {

    InputText("文本框"),
    Number("数字"),
    Password("密码"),
    SelectOne("下拉单选"),
    SelectMany("下拉多选"),
    Checkbox("多选"),
    Radio("开关"),
    Date("日期组件"),
    DateTime("日期时间组件"),
    Time("时间组件"),
    File("文件上传"),
    Image("图片上传"),
    TextArea("多行文本"),
    UserSelect("用户选择组件"),
    OrgSelect("组织选择组件"),
    RichText("富文本"),
    Region("区域选择"),
    TreeSelect("树选择"),
    Modal("自定义弹框");

    ControlType(String label) {
        this.label = label;
    }

    private String label;

    public static ControlType from(DataFieldType fieldType){
        switch (fieldType){
            case STRING:
                return ControlType.InputText;
            case INTETER:
            case DECIMAL:
                return ControlType.Number;
            case BOOLEAN:
                return ControlType.Radio;
            case DATETIME:
                return ControlType.DateTime;
            case TEXT:
                return ControlType.RichText;
            case BYTES:
                return ControlType.Image;
//            case SYSREFERENCE:
//                return ControlType.UserSelect;
            default:
                return ControlType.InputText;
        }

    }

}
