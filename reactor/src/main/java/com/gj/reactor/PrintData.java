package com.gj.reactor;

public class PrintData {

	public static void printData(Integer data) {
		System.out.println("Thread ::" + Thread.currentThread().getName() +" data :: "+ data);
	}
}
