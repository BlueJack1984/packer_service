package com.iot.constant;

import com.iot.otaBean.plmn.PlmnIndex;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统常量
 * 
 * @author Administrator
 * 
 */
public class SysConstants {
	/**
	 * 设备状态
	 */
	public static int deviceNotExist = 0;
	public static int deviceSeedNumber = (int)1;
	public static int devicePrimaryNumber = (int)2;

	/**
	 * 位置偏移数据和长度
	 */
	public static int USSDprefix_OFF = 0;
	public static int USSDprefix_Len = 7;
	public static int ProtocolVersion_OFF = USSDprefix_OFF + USSDprefix_Len;
	public static int ProtocolVersion_Len = 1;
	public static int BusiType_OFF = ProtocolVersion_OFF + ProtocolVersion_Len;
	public static int BusiType_Len = 1;
	public static int KeyID_OFF = BusiType_OFF + BusiType_Len;;
	public static int KeyID_Len = 1;
	public static int ManufacturerFlag_OFF = KeyID_OFF + KeyID_Len;;
	public static int ManufacturerFlag_Len = 5;
	public static int AppletVersion_OFF = ManufacturerFlag_OFF + ManufacturerFlag_Len;
	public static int AppletVersion_Len = 1;
	public static int CmdType_OFF = AppletVersion_OFF + AppletVersion_Len;
	public static int CmdType_Len = 1;
	public static int CipherText_OFF = CmdType_OFF + CmdType_Len;
	public static int CipherText_Len = 80;
	public static int Carrying_Capacity_OFF = CipherText_OFF + CipherText_Len;;
	public static int Carrying_Capacity_LEN = 2;
	public static int Latest_Trade_No_OFF = Carrying_Capacity_OFF + Carrying_Capacity_LEN;;
	public static int Latest_Trade_No_LEN = 20;

	public static int TRADE_ID_OFF = CmdType_OFF + CmdType_Len;
	public static int TRADE_ID_LEN = 20;
	public static int IMEI_OFF = TRADE_ID_OFF + TRADE_ID_LEN;
	public static int IMEI_LEN = 18;


	public static int ICCID_OFF = CmdType_OFF + CmdType_Len;
	public static int ICCID_LEN = 10;
	public static int R_IMEI_OFF = ICCID_OFF + ICCID_LEN;
	public static int R_IMEI_LEN = 18;
	public static int BATCH_NUM_OFF = R_IMEI_OFF + R_IMEI_LEN;
	public static int BATCH_NUM_LEN = 4;
	public static int REQUEST_INDEX_OFF = BATCH_NUM_OFF + BATCH_NUM_LEN;
	public static int REQUEST_INDEX_LEN = 4;



	/**
	 * 系统名称
	 */
	public static String SYSTEM_NAME = "设备ota系统";
	/**
	 * 所属部门 1、平台 2电信
	 */
	public static String SYSTEM_PLATFORM = "1";
	public static String SYSTEM_OPERATOR = "2";


	/**
	 * 多组通信密钥，配置成一个map
	 */
	public static Map<String, String[]> OTA_COMM_KEY_MAP = new HashMap<String, String[]>();

	/**
	 * 多组数据密钥，配置成一个map
	 */
	public static Map<String, String[]> PERS_DATA_KEY = new HashMap<String, String[]>();

	public static Map<String, String> MCC_BIT_MAP = new HashMap<String, String>();
	public static Map<String, PlmnIndex> PLMN_INDEX_MAP = new HashMap<String, PlmnIndex>();

	// ota交易 重新处理前等待时间阈值（分钟）
	// public static int OTA_TRADE_REDEAL_MINUTE = -1;
	// ota交易 最大重新处理次数
	// public static int OTA_TRADE_REDEAL_MAX_COUNT = 3;

	/**
	 * 下发副号时plmn最大长度（不包含索引的字符数）
	 */
	public static int maxPlmnLength = 60;
	/**
	 * 包含在apn中的plmn最大长度
	 */
	public static int maxPlmn2Length = 120;

	/**
	 * 更新主号短信中plmn+ussdHead最大长度 96 
	 */
//	public static int MAX_MAIN_PLMN_FIRST_LENGTH = 96;   //51改成48
	
	public static int MAX_MAIN_PLMN_FIRST_LENGTH = 60;   //30
	public static int MAX_MAIN_USSD_HEAD_FIRST_LENGTH = 36;   //18

	/**
	 * 更新主号plmn短信中plmn最大长度 222
	 */
	public static int MAX_MAIN_PLMN_SECOND_LENGTH = 222;  //114改成111
	// 更新主号短信是否存在第二条plmn短信标识
	public static String UPDATE_MAINSIM_MOREFLAG = "00";
	public static String UPDATE_MAINSIM_NOMOREFLAG = "01";
	public static String UPDATE_MAINSIM_MOREFLAG_PUSHCOMMAND = "02";
	public static String UPDATE_MAINSIM_NOMOREFLAG_PLMN_PUSHCOMMAND = "03";
	// 更新主号plmn是否存在更新主号短信标识
	public static String UPDATE_MAINPLMN_DATA_FLAG = "01";
	public static String UPDATE_MAINPLMN_NODATA_FLAG = "00";
	//套餐表中标记该套餐使用短信功能
	public static String SELECT_SOFTSIM_USESMSC = "1";
	//套餐表中标记该套餐不使用短信功能
	public static String SELECT_SOFTSIM_NOTUSESMSC = "0";
	//标记副号资源表中的短信中心字段不为空，即副号具有短信功能
	public static String SMSC_NUMBER_NOT_NULL = "1";
	/**
	 * 标记套餐是否需要短信功能
	 */
	public static String PACKAGE_SMSC_FUNTION_NO = "0";
	public static String PACKAGE_SMSC_FUNTION_YES = "1";
	/**
	 * 副号是否有通话功能。1-有通话功能；0-没有通话功能
	 */
	public static String PACKAGE_CALL_FUNTION_YES = "1";
	public static String PACKAGE_CALL_FUNTION_NO = "0";
	/**
	 * 订单状态:1：未启用；2：开始使用；3：已结束；4：已取消；5:暂停
	 */
	public static String ORDER_SATUS_NOT_START = "1";
	public static String ORDER_SATUS_START = "2";
	public static String ORDER_SATUS_END = "3";
	public static String ORDER_SATUS_CANCEL = "4";
	public static String ORDER_SATUS_PAUSE = "5";
	
	public static String ORDER_TYPE_DAILY = "0"; //日程类套餐
	public static String ORDER_TYPE_USAGE = "1"; //流量类套餐
	/**
	 * 付费方式，1-预付费；2-后付费
	 */
	public static String ORDER_BUY_TYPE_PRE = "1";
	public static String ORDER_BUY_TYPE_POST = "2";
	
	/**
	 * 设备订购轨迹信息表   asset_order_track_t  操作类型 2:启用订单,开通流量  9:暂停订单  10: 重新启用
	 * //只有流量类订单、已暂停或者未启用，才使用order_oper_type字段
	 */
	public static String ORDER_OPER_TYPE_START = "2";
	public static String ORDER_OPER_TYPE_PAUSE = "9";
	public static String ORDER_OPER_TYPE_RESTART = "10";
	/**
	 * 0非首单 1是首单
	 */
	public static String ORDER_IS_FIRST = "1";
	public static String ORDER_NOT_FIRST = "0";
	/**
	 * 订购的套餐类型，取值为1-日流量套餐；2-月流量套餐；3-季度流量套餐；4-半年流量套餐；5-年流量套餐；
	 */
	public static String ORDER_PACKAGE_TYPE_DAILY = "0";
	public static String ORDER_PACKAGE_TYPE_DAY = "1";
	public static String ORDER_PACKAGE_TYPE_MONTH = "2";
	public static String ORDER_PACKAGE_TYPE_QUATER = "3";
	public static String ORDER_PACKAGE_TYPE_HALFYEAR = "4";
	public static String ORDER_PACKAGE_TYPE_YEAR = "5";
	
	/**
	 * 订购周期的状态，取值如下：\r\n1-未开始；\r\n2-已开始；\r\n3-已废弃；\r\n4-提前结束；\r\n5-已结束\r\n
	 */
	public static String ORDER_CYCLE_STATUS_UNSTART = "1";
	public static String ORDER_CYCLE_STATUS_STARTED = "2";
	
	/**
	 * 超过每月指定时间后首月免费
	 */
	public static String FIRST_MONTH_FREE_DATE = "-11 00:00:00";
	
	
	/**
	 * 副号网关反馈状态
	 */
	public static String SOFTSIM_GATE_CODE_SUCCESS = "0";
	public static String SOFTSIM_GATE_CODE_FAILED = "1";
	/**
	 * 码号流量开通状态
	 */
	public static String SOFTSIM_FLOW_NOTOPEN= "0";
	public static String SOFTSIM_FLOW_OPEN = "1";

	/**
	 * 订单异常状态
	 */
	public static String ORDER_EXCEPTION_FLAG_NORMAL = "1";
	public static String ORDER_EXCEPTION_FLAG_ABNORMAL = "2";
	
	/**
	 * 订单异常原因类型
	 */
	public static String ORDER_OPEN_USAGE_EXCEPTION = "1";
	public static String ORDER_CLOSE_USAGE_EXCEPTION = "2";
	public static String ORDER_CHANGE_USAGE_EXCEPTION = "3";
	public static String ORDER_POR_EXCEPTION = "4";
	
	/**
	 * 设备是否使用imei作为唯一标识
	 */
	public static String IMEI_IS_NOT_ASSETID = "0";
	public static String IMEI_IS_ASSETID = "1";
	/**
	 * 渠道类型 1:USSD 2:BIP
	 */
	public static String CHANNEL_TYPE_USSD = "1";
	public static String CHANNEL_TYPE_BIP = "2";
	/**
	 * 预警事件eventcode
	 */
	public static String EVENT_CODE_NOT_HAVE_POR = "assetota0001";
	public static String EVENT_CODE_NOT_HAVE_ORDER = "assetota0002";
	
	
	public static String EVENT_CODE_CALL_ORDERSYS = "assetota0003"; //调用订单系统失败
	public static String EVENT_CODE_SYS_ERROR = "assetota0004"; //设备ota业务逻辑处理失败
	public static String EVENT_CODE_ASSET_NOT_HAVE_PNUMBER = "assetota0005"; //设备ota系统异常_设备码号表没有码号记
	public static String EVENT_CODE_ASSET_NOT_HAVE_APN = "assetota0006"; //上报设备不是卡片类型，但是副号没有apn
	
	/**
	 * 级联新协议版本号
	 */
	public static String OTA_PROTOCOL_VERSION_CASCADE = "6";
	/**
	 * 套餐使用属性: 1:常驻套餐  2:临时套餐
	 */
	public static String LONGTIME_PACKAGE = "1";
	public static String SHORTTIME_PACKAGE = "2";
	/**
	 * 是否为多imsi的设备:0否 1是
	 */
	public static String MORE_IMSI = "1";
	public static String SINGLE_IMSI = "0";
	/**
	 * 老协议设备类型
	 */
	public static String OLDVERSION_BUSITYPE_CARD = "1";
	public static String OLDVERSION_BUSITYPE_M2MCARD = "4";
	
	public static final String SESSION_EXIT = "SESSION EXIT";
	
	/**
	 * 副号状态
	 */
	public static final String LOCAL_STATUS = "LOCAL_STATUS";
	public static final String LOCAL_STATUS_REPEAT = "LOCAL_STATUS_REPEAT";
	
	/**
	 * 07-下发副号bipParam;08-下发主号bipParam'
	 */
	public static final String LOCAL_UPDATE_BIPPARAM = "07";
	public static final String GLOBAL_UPDATE_BIPPARAM = "08";
	/**
	 * 设备主号状态时是否已更新为bip参数
	 */
	public static final String BIP_IS_NOT_UPDATE = "0";
	public static final String BIP_IS_UPDATE = "1";
	/**
	 * 01-存在更新主号数据短信02-存在更新主号APN数据短信
	 */
	public static final String EXISTS_UPDATE_PNUMBER = "01";
	public static final String EXISTS_UPDATE_APN = "02";
	/**
	 * 确定副号是否已下发:0确定 1不确定
	 */
	public static final String IS_DOWNLOAD_CONFIRM = "0";
	public static final String IS_DOWNLOAD_NOTCONFIRM = "1";
	/**
	 * 确定副号是否已释放:2-已释放
	 */
	public static final String SOFTSIM_IS_RELEASED = "2";
	public static final String SOFTSIM_IS_DOWNLOAD = "1";
	public static final String SOFTSIM_NOT_DOWNLOAD = "0";
	/**
	 * 订购流量套餐目的，1-全球范围使用主号流量；2-漫游地使用副号流量
	 */
	public static final String ORDER_PURPOSE_PRIMARY = "1";
	public static final String ORDER_PURPOSE_SOFTSIM = "2";
	
}
