package com.dbsun.util;

/**
 * 项目名称：
 * @author:fh
 * 
*/
public class Const {
	public static final int MAXSENDSMSLIMITFROMSAMETEL = 3;//24小时内手机号获取验证码上限
	public static final int MAXSENDSMSLIMITFROMSAMEIP = 10;//24小时内同一IP获取验证码上限
	
	public final static String SESSION_WX_CODE = "sessionWxCode";
	public final static String SESSION_WX_OPENID = "sessionWxOpenId";	//获取用户openID
	
	
	/**拉取用户信息(需scope为 snsapi_userinfo)*/
	public final static String GETUSERINFOURL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**获取微信用户信息包括openID*/
	public final static String GETPAGEAKURL = "https://api.weixin.qq.com/sns/oauth2/access_token?"
			+ "appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";


	/**
	 * 流程控制枚举类
	 */
	public enum FluxSwitchEnum {

		AUTOSIGN(1, "待财务确认签单"),
		AUTODOSIGN(2, "待后台确认做单"),
		AUTONUM(3, "待财务自动编号"),
		NUMED(4, "已财务自动编号");

		private int code;
		private String value;

		FluxSwitchEnum(int code, String value) {
			this.code = code;
			this.value = value;
		}

		public int getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}

		public static FluxSwitchEnum codeOf(int code){
			for(FluxSwitchEnum fluxSwitchEnum : values()){
				if(fluxSwitchEnum.getCode() == code){
					return fluxSwitchEnum;
				}
			}
			throw new RuntimeException();
		}
	}

	/**
	 * 系统通知枚举类
	 */
	public enum SystemNoticeEnum {

		CANDOSIGN(5, "后台确认做单"),
		BACDOSIGN(8, "后台做单"),
		LENDING(10, "后台放贷"),
		COSTENTER(13, "成本录入"),
		SETTLEACCOUNT(14, "已结账");

		private int code;
		private String value;

		SystemNoticeEnum(int code, String value) {
			this.code = code;
			this.value = value;
		}

		public int getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}

		public static SystemNoticeEnum codeOf(int code){
			for(SystemNoticeEnum systemNoticeEnum : values()){
				if(systemNoticeEnum.getCode() == code){
					return systemNoticeEnum;
				}
			}
			throw new RuntimeException();
		}


	}


}
