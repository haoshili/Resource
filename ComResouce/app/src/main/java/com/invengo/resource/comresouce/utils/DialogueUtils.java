package com.invengo.resource.comresouce.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.invengo.resource.comresouce.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DialogueUtils {

	private AlertDialog alertDialog;

	private TextView tv_diaTitle;
	private TextView btn_cancel;
	private TextView btn_ok;
	private TextView tv_body;

	private View view;

	private Context context;

	private Button btn_left;
	private Button btn_right;

	private RelativeLayout re_head;
	private RelativeLayout re_foot;

	/**
	 * 弹出对话框的类别
	 */
	private int alertDialogType;
	private int textLong;

	/**
	 * 弹出框标识码
	 */
	private int code;

	public DialogueUtils(Context context) {
		this(context, 0);
		this.context = context;
	}

	public DialogueUtils(Context context, int alertDialogType) {
		this.alertDialogType = alertDialogType;
		this.context = context;
		alertDialog = new AlertDialog.Builder(context).create();
		view = View.inflate(context, R.layout.dialog, null);
		re_head = (RelativeLayout) view.findViewById(R.id.re_title);
		re_foot = (RelativeLayout) view.findViewById(R.id.re_foot);
		tv_diaTitle = (TextView) view.findViewById(R.id.tv_diaTitle);
		tv_body = (TextView) view.findViewById(R.id.tv_body);
		btn_cancel = (TextView) view.findViewById(R.id.btn_cancel);
		btn_ok = (TextView) view.findViewById(R.id.btn_ok);
		setButtonClick();
	}

	public DialogueUtils(Context context, int alertDialogType,
			ClickListenser listenser) {
		this(context, alertDialogType);

		registerListenser(listenser);
		setButtonClick();
	}

	public DialogueUtils(Context context, int alertDialogType,
			ClickListenser listenser, int code) {
		this(context, alertDialogType, listenser);
		this.code = code;
	}

	// ================================Show=====================================

	public void showDia() {
		alertDialog.setView(view);
		alertDialog.show();
	}

	public void showDiaNoCancel() {
		alertDialog.setCancelable(false);
		showDia();
	}

	/**
	 * 显示加载对话框
	 */
	public void showProgressDialogue() {
		view = View.inflate(context, R.layout.dia_progress, null);
		showDiaNoCancel();
	}

	/**
	 * 不显示数据
	 * 
	 * @param clearHead
	 * @param clearFoot
	 */
	public void clear(boolean clearHead, boolean clearFoot) {

		if (clearHead) {
			re_head.setVisibility(View.GONE);
		}
		if (clearFoot) {
			re_foot.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置按钮点击情况
	 * 
	 * @param left
	 * @param right
	 */
	public void setFoot(String left, String right) {

		btn_cancel.setText(left);
		btn_ok.setText(right);
	}

	// ================================Title=====================================

	public void setTitle(String text) {
		tv_diaTitle.setText(text);
	}

	public void setTitle(String text, int colour) {
		tv_diaTitle.setText(text);
		tv_diaTitle.setTextColor(colour);
	}

	// ================================Body=====================================

	public void setBody(String text) {
		tv_body.setText(text);
	}

	public void setTitle(int resource) {
		View v = View.inflate(context, resource, null);
		RelativeLayout re_title = (RelativeLayout) view
				.findViewById(R.id.re_title);
		re_title.removeAllViews();
		re_title.addView(v);
	}

	// ================================Body=====================================

	public void setBody(List<Map<String, String>> listData) {
		RelativeLayout re_body = (RelativeLayout) view
				.findViewById(R.id.re_body);
		re_body.removeAllViews();
		View bodyView = View
				.inflate(context, R.layout.dialogue_body_list, null);

		ListView listView = (ListView) bodyView.findViewById(R.id.lv_dia);

		SimpleAdapter adapter = new SimpleAdapter(context, listData,
				R.layout.dialogue_body_list_item, new String[] { "data" },
				new int[] { R.id.tv_dia_list_item });

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				listenser.onListViewItemClick(position, alertDialogType, code,
						alertDialog);
				alertDialog.dismiss();
			}
		});

		listView.setAdapter(adapter);
		re_body.addView(bodyView);
	}

	private TextView tv_des;
	private String judgeText = "no";

	/**
	 * 该方法会显示提示的信息
	 * 
	 * @param textLong
	 * @param text
	 */
	public void setBody(final int textLong, String text, String judge) {
		this.judgeText = judge;
		setBody(textLong);
		tv_des.setVisibility(View.VISIBLE);
		tv_des.setText(text);
	}

	/**
	 * 显示编辑框
	 * 
	 * @param textLong
	 */
	public void setBody(final int textLong) {

		this.textLong = textLong;
		RelativeLayout re_body = (RelativeLayout) view
				.findViewById(R.id.re_body);
		re_body.removeAllViews();
		View bodyView = View.inflate(context, R.layout.dialogue_edit, null);
		final EditText editText = (EditText) bodyView.findViewById(R.id.et_dia);
		tv_des = (TextView) bodyView.findViewById(R.id.tv_des);

		InputFilter[] filters = { new LengthFilter(textLong) };
		editText.setFilters(filters);
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				listenser.onTextChangeListenser(s.toString(), code);
				if (s.length() > 0) {

					if (judgeText.equals("no")
							|| s.toString().equals(judgeText.trim())) {
						setBtnRightClick();
						setTextColour(true);
					}
				} else {
					btn_right.setClickable(false);
					setTextColour(false);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		re_body.addView(bodyView);
	}

	public void setBody(String[] data) {
		RelativeLayout re_body = (RelativeLayout) view
				.findViewById(R.id.re_body);
		re_body.removeAllViews();
		View bodyView = View
				.inflate(context, R.layout.dialogue_body_list, null);

		ListView listView = (ListView) bodyView.findViewById(R.id.lv_dia);

		SimpleAdapter adapter = new SimpleAdapter(context, dataChanger(data),
				R.layout.dialogue_body_list_item, new String[] { "data" },
				new int[] { R.id.tv_dia_list_item });

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				alertDialog.dismiss();
				listenser.onListViewItemClick(position, alertDialogType, code,
						alertDialog);
			}
		});

		listView.setAdapter(adapter);
		re_body.addView(bodyView);
	}

	public void setBodyChart(String[] data) {
		RelativeLayout re_body = (RelativeLayout) view
				.findViewById(R.id.re_body);
		re_body.removeAllViews();
		View bodyView = View.inflate(context,
				R.layout.dialogue_body_list_chart, null);

		ListView listView = (ListView) bodyView.findViewById(R.id.lv_dia);

		SimpleAdapter adapter = new SimpleAdapter(context, dataChanger(data),
				R.layout.dialogue_body_list_item, new String[] { "data" },
				new int[] { R.id.tv_dia_list_item });

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				alertDialog.dismiss();
				listenser.onListViewItemClick(position, alertDialogType, code,
						alertDialog);
			}
		});

		listView.setAdapter(adapter);
		re_body.addView(bodyView);
	}

	public void disMiss() {
		alertDialog.dismiss();
	}

	private List<Map<String, String>> dataChanger(String[] data) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		for (String item : data) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("data", item);
			list.add(map);
		}
		return list;
	}

	private void setButtonClick() {
		btn_left = (Button) view.findViewById(R.id.btn_cancel);
		btn_right = (Button) view.findViewById(R.id.btn_ok);

		btn_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listenser.onDialogueClick(R.id.btn_cancel, alertDialogType,
						code, alertDialog);
				alertDialog.dismiss();
			}
		});
		btn_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listenser.onDialogueClick(R.id.btn_ok, alertDialogType, code,
						alertDialog);
				alertDialog.dismiss();
			}
		});

		if (alertDialogType == DialogueType.EDIT) {
			btn_right.setClickable(false);
			setTextColour(false);
		}
	}

	private void setBtnRightClick() {
		setTextColour(true);
		btn_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listenser.onDialogueClick(R.id.btn_ok, alertDialogType, code,
						alertDialog);
				alertDialog.dismiss();
			}
		});
	}

	/**
	 * 设置右边字体颜色
	 * 
	 * @param isGreen
	 */
	private void setTextColour(boolean isGreen) {
		if (isGreen) {
			btn_right.setTextColor(0xff0d91a0);
		} else {
			btn_right.setTextColor(0xff888888);
		}
	}

	public void registerListenser(ClickListenser listenser) {
		this.listenser = listenser;
	}

	private ClickListenser listenser;

	public interface ClickListenser {
		public void onDialogueClick(int resource, int type, int code,
									AlertDialog alertDialog);

		public void onListViewItemClick(int position, int type, int code,
										AlertDialog alertDialog);

		public void onTextChangeListenser(String text, int code);
	}

	public class DialogueType {

		public static final int NORMAL = 1;
		public static final int EDIT = 2;
		public static final int LIST = 3;
		public static final int LIST_TOW = 4;

	}
}
