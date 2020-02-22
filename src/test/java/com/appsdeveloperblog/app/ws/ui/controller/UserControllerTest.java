//package com.appsdeveloperblog.app.ws.ui.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class UserControllerTest {
//	
//	@Autowired
//	private MockMvc mockMvc;
//	private UserDetailsRequestModel userDetailsRequestModel = new UserDetailsRequestModel();
//	
//	
//	 @BeforeClass
//	    public void setup() {
//			userDetailsRequestModel = new UserDetailsRequestModel();
//			userDetailsRequestModel.setFirstName("Sergey");
//			userDetailsRequestModel.setFirstName("Sergey");
//	        userDetailsRequestModel.setLastName("Kargopolov");
//	        userDetailsRequestModel.setEmail("test@test.com");
//	    }
//	
//	
//	@Test
//	public void testCreateUser() throws Exception {
//		mockMvc.perform(
//					post("/users")
//					.content(asJsonString(userDetailsRequestModel))
//					.contentType(MediaType.APPLICATION_JSON)
//					.accept(MediaType.APPLICATION_JSON)
//				)
//			.andExpect(status().isOk())
//			.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Sergey"))
//			.andExpect(MockMvcResultMatchers.jsonPath("$.userId").exists());
//		
//	}
//	
//	
//	public static String asJsonString(final Object obj) {
//	    try {
//	        return new ObjectMapper().writeValueAsString(obj);
//	    } catch (Exception e) {
//	        throw new RuntimeException(e);
//	    }
//	}
//
//}
