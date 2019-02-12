package com.githubexample;

import java.util.List;

import com.githubexample.entities.Data;

public interface IPrinter<T> {
	 void Print();
	void SetData(List<Data> data);
}
