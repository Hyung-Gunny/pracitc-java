/*
 * 파일명: chap06_Homework2_22112204_박형건 
 * 프로그램의 목적 및 기능 : Thesaurus Dictionary 구현이다.
 * - class Word_Type
 * 프로그램 작성자 : 박형건 (2023/10/14)
 * 학번 : 22112204
 * 최종 수정일: 2023.10.14
 */
package chap06_Homework2_22112204_박형건;

public class Word_Type 
{
	private String word;
	private String type;
	//생성자로서 값을 초기화 시켜주고 있다.
	public Word_Type(String word, String type)
	{
		this.word=word;
		this.type=type;
		
	}
	
	//값을 해쉬 코드로서 출력하게 도와주고 있다.
	public int hashcode()
	{
		return word.hashCode()+type.hashCode();
		
	}
	//맞는지 틀리지를 판단하도록 하는 함수이다.
	public boolean equals(Object obj)
	{
		if(this==obj)
		{
			return true;
		}
		if(obj==null || getClass()!=obj.getClass())
		{
			return false;
		}
		
		Word_Type other=(Word_Type) obj;
		
		return word.equals(other.word)&&type.equals(other.type);
	}
	
	//출력 할 수 있도록 도와주는 함수이다.
	public String toString()
	{
		String str=String.format(word + "( "+type+" )");
		return str;
	}

}
