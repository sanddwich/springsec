package com.example.springsec.config;

import com.example.springsec.entities.Privilege;
import com.example.springsec.services.BaseDataService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DefaultDataSetterTest {

	private DefaultDataSetter dataSetter;

//	@BeforeEach
//	void before(){
//		BaseDataService localMockRepository = Mockito.mock(BaseDataService.class);
//		Mockito.when(localMockRepository.findAll()).thenReturn(Collections.singletonList(new Privilege()));
//
//
//		dataSetter = new DefaultDataSetter(localMockRepository);
//	}

	@Test
	void createPrivileges() {
	}

	@Test
	void passwordEncoder() {
	}
}