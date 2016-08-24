package com.invengo.resource.comresouce.activity.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.atid.lib.dev.ATRfidManager;
import com.atid.lib.dev.ATRfidReader;
import com.atid.lib.dev.event.RfidReaderEventListener;
import com.atid.lib.dev.rfid.exception.ATRfidReaderException;
import com.atid.lib.dev.rfid.type.ActionState;
import com.atid.lib.dev.rfid.type.BankType;
import com.atid.lib.dev.rfid.type.ConnectionState;
import com.atid.lib.dev.rfid.type.ResultCode;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-29
 * Time: 15:49
 */
public class ReadTagNewActivity extends BaseTwoActivity implements RfidReaderEventListener {

    public ATRfidReader mReader = null;
    public boolean hasReasult = false;
    private ReadTagListenser listenser;
    private String TAG = "ReadTidActivity+test";
    private int OUT_TIME = 1000;

    //***************读写器模块初始化*********************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Reader
        if ((mReader = ATRfidManager.getInstance()) == null) {
            listenser.onNoReady();
            Log.e(TAG, "ERROR. onCreate() - Failed to get reader instance");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        if (mReader != null) ATRfidManager.wakeUp();
        super.onStart();
    }

    @Override
    protected void onResume() {

        try {
            if (mReader == null) {

            } else {
                mReader.setEventListener(this);
                mReader.setPower(260);
            }

        } catch (ATRfidReaderException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mReader != null) mReader.removeEventListener(this);
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        if (mReader != null) ATRfidManager.sleep();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mReader != null) ATRfidManager.onDestroy();
        super.onDestroy();
    }

    //******************数据返回值******************

    @Override
    public void onReaderStateChanged(ATRfidReader atRfidReader, ConnectionState connectionState) {

    }

    @Override
    public void onReaderActionChanged(ATRfidReader atRfidReader, ActionState actionState) {

    }

    @Override
    public void onReaderReadTag(ATRfidReader atRfidReader, String s, float v) {

    }

    @Override
    public void onReaderResult(ATRfidReader atRfidReader, ResultCode resultCode, ActionState
            actionState, String epc, String data) {

        if (epc.trim().length() > 0) {
            hasReasult = true;
        }

        handleReasultData(resultCode, epc, data);
    }

    //******************对外公布的方法******************

    /**
     * 采用默认的设置读取标签
     * <p/>
     * 1.默认超时时间800ms
     * <p/>
     * 2.默认的失败重读次数3次
     *
     * @return
     */
    public boolean readTid() {
        showLog("读取标签TID#####");
        bank = BankType.valueOf(BankType.TID.getValue());
        return excuteReader();
    }

    /**
     * 读取epc
     *
     * @return
     */
    public boolean readEpc() {
        showLog("读取标签EPC####");
        bank = BankType.valueOf(BankType.EPC.getValue());
        return excuteReader();
    }

    /**
     * 带密码读取epc
     *
     * @param pwd 密码
     * @return
     */
    public boolean readEpc(String pwd) {
        CURE_TIMES = 0;
        showLog("带密码读取EPC*****密码" + pwd);
        this.pwd = pwd;
        bank = BankType.valueOf(BankType.EPC.getValue());
        return excuteReader();
    }


    //******************配置方法******************

    public void setListenster(ReadTagListenser listenser) {
        this.listenser = listenser;
    }

    /**
     * 设置失败重读次数
     * <p/>
     * 该功能主要针对带密码读取epc的
     *
     * @param times 失败重读次数
     */
    public void setReadTimes(int times) {
        FAILED_TRY = times;
    }

    /**
     * 设置读取标签的超时时间
     * <p/>
     * 在标签读取的过程中，如果读不到标签，读写器会一直工作，设置超时间提示用户更换标签或者距离标签近一些等操作
     *
     * @param time 超时时间，默认时间未1000ms，单位为ms
     */
    public void setOutReadTime(int time) {
        if (time > OUT_TIME) {
            this.OUT_TIME = time;
        }
    }

    /**
     * 设置读取标签Tid的长度
     *
     * @param is128Lengths 是否是128位的标签
     */
    public void setReadTidLendth(boolean is128Lengths) {

        if (is128Lengths) {
            READLENTH = 8;
            showLog("设置读取tid的长度" + READLENTH);
        } else {
            READLENTH = 6;
            showLog("设置读取tid的长度" + READLENTH);
        }
    }

    public void setReadLength(int readLength) {
        READLENTH = readLength;
        showLog("设置读取tid的长度" + readLength);
    }

    //*******************私有工作方法**********************

    private BankType bank;
    private String pwd;

    /**
     * 128位的标签是8,96位标签的长度是6.默认情况先读取128，如果超出该标签长度，会设置为6
     **/
    private int READLENTH = 6;

    private boolean excuteReader() {

        //将该字段设置为false；
        hasReasult = false;

        if (mReader == null) {
            showLog("读写器没有准备好");
            return false;
        } else {
            ResultCode res;
            if (mReader.getAction() == ActionState.Stop) {
                if ((res = mReader.readMemory6c(bank, 0, READLENTH, pwd)) == ResultCode.NoError) {
                    handler.sendEmptyMessageDelayed(CHECK_REASULT, OUT_TIME);
                    return true;
                } else {
                    showLog("第一次读取失败，进行重新读取");
                    return true;
                }
            } else {
                showLog("读写器繁忙");
                return false;
            }

        }
    }

    /**
     * 对返回的数据结果进行处理
     * <p/>
     * 1.读取tid时候，位数的判断
     * 2.判断返回结果是否为空
     *
     * @param resultCode 返回的数据结果代码
     * @param epc        每次读取标签都会返回epc数据
     * @param data       请求读取的目标数据
     */
    private void handleReasultData(ResultCode resultCode, String epc, String data) {
        showLog("显示结果***" + data + "***epc***" + epc + "***&&&code&&&***" + resultCode.toString());

        //返回结果，关闭结果查询系统
        if (bank == BankType.valueOf(BankType.TID.getValue())) {
            handlTidData(resultCode, epc, data);
        } else {
            handlEpcData(resultCode, epc, data);
        }
    }

    /**
     * 处理返回的tid数据
     */
    private void handlTidData(ResultCode resultCode, String epc, String data) {
        handler.removeMessages(CHECK_REASULT);
        if (resultCode == ResultCode.MemoryOverrun) {
            //设置读取长度，重新读取tid，开启结果检测程序
            setReadTidLendth(false);
            handler.sendEmptyMessageDelayed(READ_TAG, 300);
            handler.sendEmptyMessageDelayed(CHECK_REASULT, 300 + OUT_TIME);
        } else {
            //获取到结果，返回数据
            if (listenser != null) {
                showLog("返回数据=======data为读取到的tid结果=======" + data);
                listenser.onReaderResult(data, epc, resultCode);
            } else {
                showLog("读取到结果*********未设置监听********" + data);
            }
        }

    }

    private int FAILED_TRY = 3;
    private int CURE_TIMES = 0;

    /**
     * 处理epc数据
     */
    private void handlEpcData(ResultCode resultCode, String epc, String data) {
        handler.removeMessages(CHECK_REASULT);
        if (resultCode == ResultCode.OutOfRetries) {
            //设置读取长度，重新读取tid，开启结果检测程序

            if (CURE_TIMES >= FAILED_TRY) {
                //获取到结果，返回数据
                if (listenser != null) {
                    showLog("返回数据=======data为读取到的EPC结果=======" + CURE_TIMES);
                    listenser.onReaderResult(data, epc, resultCode);
                } else {
                    showLog("读取到结果*********未设置监听********" + data);
                }
            } else {

                showLog("============未读取到结果===============" + CURE_TIMES);
                handler.sendEmptyMessageDelayed(READ_TAG, 300);
                handler.sendEmptyMessageDelayed(CHECK_REASULT, 300 + OUT_TIME);
                CURE_TIMES++;

                showLog(FAILED_TRY + " ============未读取到结果===============" + CURE_TIMES);
            }


        } else {
            //获取到结果，返回数据
            if (listenser != null) {
                showLog("返回数据=======data为读取到的Ecp结果=======" + data);
                listenser.onReaderResult(data, epc, resultCode);
            } else {
                showLog("读取到结果*********未设置监听********" + data);
            }
        }
    }

    //*****************************Hanlder处理程序*******************************************

    private final static int READ_TAG = 1;
    private final static int CHECK_REASULT = 2;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case READ_TAG:
                    excuteReader();
                    break;

                case CHECK_REASULT:
                    checkReasult();
                default:
                    break;
            }
        }
    };

    /**
     * 延时检测是否读取到结果
     */
    private void checkReasult() {

        if (!hasReasult) {
            mReader.stop();
        }

        if (listenser != null) {
            showLog("返回数据=======没有读取到结果===有监听====");
            listenser.onNullReasult();
        } else {
            showLog("返回数据=======没有读取到结果=====未设置监听==");
        }
    }

    //*****************************帮助方法***********************************************

    public void showLog(String message) {
        //如果不想打印log，直接注释
        Log.i(TAG, message);
    }

    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {

    }

    public interface ReadTagListenser {

        public void onNullReasult();

        public void onNoReady();

        public void onReaderResult(String data, String epc, ResultCode code);

        public void onReaderStateChanged(ConnectionState arg1);

        public void onReaderReadTag(String arg1, float arg2);

        public void onReaderActionChanged(ActionState arg1);
    }
}