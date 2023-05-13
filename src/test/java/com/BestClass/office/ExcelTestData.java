package com.BestClass.office;

import com.creditdatamw.zerocell.annotation.Column;

public class ExcelTestData {
	
@Column(name="firstName", index=0)
private String firstName;

@Column(name="middleName", index=1)
private String middleName;

@Column(name="lastName", index=2)
private String lastName;

@Column(name="fromCountry", index=3)
private String fromCountry;

@Column(name="toCountry", index=4)
private String toCountry;

@Column(name="birthMonth", index=5)
private String birthMonth;

@Column(name="birthDay", index=6)
private String birthDay;

@Column(name="birthYear", index=7)
private String birthYear;

@Column(name="mailId", index=8)
private String mailId;

@Column(name="areaCode", index=8)
private String areaCode;

@Column(name="phoneNumber", index=8)
private String phoneNumber;
}
