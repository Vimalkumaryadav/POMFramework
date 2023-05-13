package com.BestClass.office;

import com.creditdatamw.zerocell.converter.Converter;

public class StringToInteger implements Converter<Integer> {
	@Override
	public Integer convert(String value,String columName, int rowNumber) { return Integer.parseInt(value);}
}
