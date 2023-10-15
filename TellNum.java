/*
 * 파일명: chap06_Homework1_22112204_박형건 
 * 프로그램의 목적 및 기능 : TreeMap<String, Person>기반 주소록 구현.
 * - class TelNum
 * 프로그램 작성자 : 박형건 (2023/10/14)
 * 학번 : 22112204
 * 최종 수정일: 2023.10.14
 */
package chap06_Homework1_22112204_박형건;

import java.util.TreeMap;
import java.util.Set;


public class TelNum 
{
	private int nation_code;
    private int region_number;
    private int switch_number;
    private int line_number;

    public TelNum(int nation_code, int region_number, int switch_number, int line_number) {
        this.nation_code = nation_code;
        this.region_number = region_number;
        this.switch_number = switch_number;
        this.line_number = line_number;
    }

    @Override
    public String toString() 
    {
        return String.format("%03d-%d-%d-%d", nation_code, region_number, switch_number, line_number);
    }

	

}
