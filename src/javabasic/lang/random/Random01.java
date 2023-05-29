package javabasic.lang.random;

import java.util.Random;
import java.util.StringJoiner;

public class Random01 {
	final static String TABLE_NAME = "Dummy";
	final static Long ROW_COUNT = 100L;
	final static StringBuilder sb = new StringBuilder();
	final static StringBuilder sb2 = new StringBuilder(200);
	
	static {
		StringJoiner sj = new StringJoiner(",","(",")");
		sb.append("INSERT INTO ").append(TABLE_NAME);
		for(COLUME col : COLUME.values()) {
			sj.add(col.toString());
		}
		sb.append(sj.toString());
		sb.append("VALUES ");
		
		for(int i=0;i<ROW_COUNT;i++) {
			StringJoiner values = new StringJoiner(",","(",")");
			for(COLUME col : COLUME.values()) {
				values.add("'"+col.getValue()+"'");
			}
			System.out.print(sb.toString());
			System.out.println(values.toString());
		}
	}
	
	
	private enum COLUME{
		PHONE_NUM {
			String getValue() {
				return PHONE_NUM_DB[random.nextInt(PHONE_NUM_DB.length)];
			}
		}, GENDER {
			String getValue() {
				return GENDER_DB[random.nextInt(GENDER_DB.length)];
			}
		}, AGE {
			String getValue() {
				return AGE_DB[random.nextInt(AGE_DB.length)];
			}
		};
		final Random random = new Random();
		final String[] PHONE_NUM_DB = {"010","011","017","018","019"};
		final String[] GENDER_DB = {"남","여"};
		final String[] AGE_DB = {"10대","20대","30대","40대","50대"};
		
		abstract String getValue();
	}
	public static void main(String[] args) {
		
	}
}
