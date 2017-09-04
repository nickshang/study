package stream;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SysConstant {
  /**分析中数据保留的小数点的位数.*/
  public static final int LOAD_PRECISION = 2;
  /**百分数保留的小数点的位数.*/
  public static final int PRECISION_PERCENT = 1;
  /**系统操作的负荷日志是否从0点开始。*/
  public static final boolean DAY_LOAD_CURVE_START_WITH_ZERO=false;
  /**系统操作的负荷日志点数，默认为96点。*/
  public static final int DAY_LOAD_CURVE_POINT_NUM=96;
  /**24点负荷日志的列名。*/
  public static final String[] DAY_LOAD_CURVE_24COLUMN=new String[24];
  /**48点负荷日志的列名。*/
  public static final String[] DAY_LOAD_CURVE_48COLUMN=new String[48];
  /**96点负荷日志的列名。*/
  public static final String[] DAY_LOAD_CURVE_96COLUMN=new String[96];
  /**288点负荷日志的列名。*/
  public static final String[] DAY_LOAD_CURVE_288COLUMN=new String[288];
  /**1440点负荷日志的列名。*/
  public static final String[] DAY_LOAD_CURVE_1440COLUMN=new String[1440];
  static {
    SimpleDateFormat sdf=new SimpleDateFormat("HHmm");
    Calendar calendar=Calendar.getInstance();
    calendar.set(2000,0,1,0,0,0);
    if(!DAY_LOAD_CURVE_START_WITH_ZERO) calendar.add(Calendar.MINUTE,30);
    for(int i=0;i<48;i++){
      String time = sdf.format(calendar.getTime());
      if(!DAY_LOAD_CURVE_START_WITH_ZERO && "0000".equals(time)){
        time="2400";
      }
      DAY_LOAD_CURVE_48COLUMN[i]="T"+time;
      calendar.add(Calendar.MINUTE,30);
    }
    calendar.set(2000,0,1,0,0,0);
    if(!DAY_LOAD_CURVE_START_WITH_ZERO) calendar.add(Calendar.MINUTE,15);
    for(int i=0;i<96;i++){
      String time = sdf.format(calendar.getTime());
      if(!DAY_LOAD_CURVE_START_WITH_ZERO && "0000".equals(time)){
        time="2400";
      }
      DAY_LOAD_CURVE_96COLUMN[i]="T"+time;
      calendar.add(Calendar.MINUTE,15);
    }
    calendar.set(2000,0,1,0,0,0);
    if(!DAY_LOAD_CURVE_START_WITH_ZERO) calendar.add(Calendar.MINUTE,5);
    for(int i=0;i<288;i++){
      String time = sdf.format(calendar.getTime());
      if(!DAY_LOAD_CURVE_START_WITH_ZERO && "0000".equals(time)){
        time="2400";
      }
      DAY_LOAD_CURVE_288COLUMN[i]="T"+time;
      calendar.add(Calendar.MINUTE,5);
    }
    calendar.set(2000,0,1,0,0,0);
    if(!DAY_LOAD_CURVE_START_WITH_ZERO) calendar.add(Calendar.MINUTE,60);
    for(int i=0;i<24;i++){
      String time = sdf.format(calendar.getTime());
      if(!DAY_LOAD_CURVE_START_WITH_ZERO && "0000".equals(time)){
        time="2400";
      }
      DAY_LOAD_CURVE_24COLUMN[i]="T"+time;
      calendar.add(Calendar.MINUTE,60);
    }

    calendar.set(2000,0,1,0,0,0);
    if(!DAY_LOAD_CURVE_START_WITH_ZERO) calendar.add(Calendar.MINUTE,1);
    for(int i=0;i<1440;i++){
      String time = sdf.format(calendar.getTime());
      if(!DAY_LOAD_CURVE_START_WITH_ZERO && "0000".equals(time)){
        time="2400";
      }
      DAY_LOAD_CURVE_1440COLUMN[i]="T"+time;
      calendar.add(Calendar.MINUTE,1);
    }
  }
}

