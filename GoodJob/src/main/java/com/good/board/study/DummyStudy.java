package com.good.board.study;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Random;

import com.good.board.model.StudyDTO;
import com.good.board.repository.StudyDAO;

public class DummyStudy {
	public static void main(String[] args) {
		String[] subjects = { "스터디", "모임", "취준", "프로젝트", "그룹" };
		String[] objects = { "새로운 멤버", "팀원", "동료", "참가자", "파트너" };
		String[] verbs = { "모집", "찾아요", "구해요", "구함"};
		int[] numbers = {
	            2375, 2408, 2241, 2105, 2191, 2303, 10138, 3861, 2969, 2402,
	            2799, 2823, 2707, 2937, 8527, 3510, 8843, 2890, 2456, 5151,
	            3570, 9022, 8360, 3241, 3142, 3467, 8478, 3891, 8912, 4380,
	            7524, 5403, 3464, 7831, 2499, 4184, 2827, 2298, 7717, 4524,
	            3944, 6410, 4082, 3096, 4656, 3258, 3838, 6664, 3912, 9654,
	            6007, 2219, 2781, 7208, 3845, 4693, 6136, 10533, 5377, 2902,
	            4374, 5972, 8315, 4509, 3441, 4763, 7310, 3600, 5370, 4586,
	            7711, 7802, 7917, 4238, 4358, 5258, 7669, 2930, 5466, 5998,
	            4664, 4492, 11413, 8073, 5179, 4759, 7632, 9953, 3921, 3789,
	            4360, 5252, 5453, 8606, 8606, 5656, 5798, 4812, 3213, 4076,
	            4432, 9619, 9591, 4813, 8141, 6251, 4575, 4575, 9157, 7858,
	            2944, 4865, 11478, 5644, 11852, 5094, 6028, 4915, 4887, 10047,
	            7171, 10462, 3427, 6083, 5374, 3440, 4201, 9092, 3232, 4421,
	            4989, 10046, 6576, 9291, 8016, 9383, 6317, 10971, 4391, 2874,
	            7149, 3143, 4779, 11768, 5826, 5333, 7723, 11661, 6414, 3419,
	            9831, 4921, 4898, 4007, 5307, 9216, 4009, 6148, 3633, 8505,
	            2607, 8887, 11083, 5766, 4696, 5409, 3070, 5572, 3484, 11261,
	            5742, 8722, 3739, 8162, 7357, 7123, 11185, 4764, 9315, 8248,
	            8643, 6962, 3322, 4146, 5321, 7588, 8464
	        };
		
		StudyDAO dao = new StudyDAO();
		
		Calendar c1 = Calendar.getInstance();
		
		c1.add(Calendar.MONTH, -3);
		
		Random rnd = new Random();
		//70% 확률로 조작
		int[] seed = {1,1,1,1,1,1,1,0,0,0};
		//2~5회 중 랜덤
		int[] seed2 = {2,3};
		
		while (!String.format("%tF", c1).equals("2024-05-06")) {
			int result = seed[rnd.nextInt(seed.length)];
			
			if(result ==1) {
				
				//글 추가
				StudyDTO dto = new StudyDTO();
				dto.setStd_content("내용");
				dto.setId("hong123");
				
				Calendar copy = (Calendar)c1.clone();
				
				for(int i=0;i<seed2[rnd.nextInt(seed2.length)];i++) {
					String subject = subjects[rnd.nextInt(subjects.length)]
							+" "+ objects[rnd.nextInt(objects.length)]
							+" "+verbs[rnd.nextInt(verbs.length)];
					String cp_seq = numbers[rnd.nextInt(numbers.length)]+"";
					dto.setStd_title(subject);
					dto.setCp_seq(cp_seq);
					dto.setStd_regdate(String.format("%tF", copy));
					copy.add(Calendar.DATE, i*3);
					dto.setStd_duedate(String.format("%tF", copy));
					
					dao.addStudy(dto);
				}
			}
			
			c1.add(Calendar.DATE, 1);
			//System.out.printf("%tF\r\n", c1);
		}
		System.out.println("완료");
	}
}
