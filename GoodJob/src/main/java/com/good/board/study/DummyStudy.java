package com.good.board.study;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Random;

import com.good.board.model.StudyDTO;
import com.good.board.repository.StudyDAO;
import com.test.util.DBUtil;

public class DummyStudy {
	public static void main(String[] args) {
		Connection conn;
		Statement stat;
		PreparedStatement pstat;
		ResultSet rs;

		conn = DBUtil.open();

		dummyReview(conn);
	}

	private static void dummyTag(Connection conn) {
		PreparedStatement pstat;
		int[] good = { 1, 2, 5, 7, 8, 9, 10, 11, 13, 15, 16, 18, 23, 24, 25, 26, 27, 28, 31, 32, 33, 35, 39, 43, 44, 49,
				50, 51, 52, 53, 54, 55 };
		int[] bad = { 4, 6, 12, 17, 29, 30, 36, 37, 38, 40, 41, 42, 45, 46, 47, 48 };
		int[] soso = { 19, 20, 21, 22, 34 };

		Random rnd = new Random();
		int[] seed = { 1, 0, 0 };

		for (int i = 161; i < 986; i++) {

			for (int j = 0; j < 3; j++) {
				int result = seed[rnd.nextInt(seed.length)];

				int ran = 0;
				if (j < 2) {
					ran = good[rnd.nextInt(good.length)];
				}
				if (j == 2) {
					if (result == 1) {
						ran = bad[rnd.nextInt(bad.length)];
					} else {
						ran = soso[rnd.nextInt(soso.length)];
					}
				}
				try {
					String sql = "insert into tblreviewtag values (seqreviewtag.nextval, ?,?)";

					pstat = conn.prepareStatement(sql);
					pstat.setInt(1, ran);
					pstat.setInt(2, i);

					pstat.executeUpdate();
					pstat.close();

				} catch (Exception e) {
					System.out.println("dummy.add");
					e.printStackTrace();
				}
			}
		}
		System.out.println("완료");
	}

	private static void dummyWel(Connection conn) {
		PreparedStatement pstat;
		int[] numbers = { 5870, 5931, 2640, 2219, 2191, 3722, 8138, 7579,
	            6790, 3102, 3385, 2105, 7751, 2594, 2298, 2241,
	            7689, 4699, 4663, 9409, 5981, 4687, 6854, 3252,
	            10329, 5665, 9163 };

		Random rnd = new Random();
		// 70% 확률로 조작
		int[] seed = { 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };

		for (int i = 0; i < numbers.length; i++) {

			int cp_seq = numbers[i];

			for (int j = 1; j < 70; j++) {
				int result = seed[rnd.nextInt(seed.length)];
				if (result == 1) {

					try {
						String sql = "insert into tblwelfarelist values (?, ?)";

						pstat = conn.prepareStatement(sql);
						pstat.setInt(1, cp_seq);
						pstat.setInt(2, j);

						pstat.executeUpdate();
						pstat.close();

					} catch (Exception e) {
						System.out.println("dummy.add");
						e.printStackTrace();
					}

				}
			}

		}
		System.out.println("완료");
	}

	private static void dummyReview(Connection conn) {
		PreparedStatement pstat;
		// 기업의 특성을 나타내는 배열
		String[] qualities = { "연봉", "복지", "회사 문화", "워라밸", "성장 가능성" };

		// 평가를 나타내는 배열
		String[] evaluations = { "매우 좋음", "괜찮음", "보통", "별로", "매우 나쁨" };

		// 리뷰를 종결하는 문장 배열
		String[] conclusions = { "전반적으로 괜찮아요.", "야근이 잦아요.", "성장할 기회가 많아요.", "팀 분위기가 좋지 않아요.", "복지가 훌륭해요." };
		// 긍정적인 리뷰를 나타내는 배열
		String[] goodReviews = { "연봉이 업계 최고 수준입니다.", "복지가 매우 훌륭하며 직원을 잘 챙깁니다.", "회사 문화가 개방적이어서 의견을 자유롭게 나눌 수 있습니다.",
				"워라밸이 잘 지켜지며 자유로운 분위기입니다.", "성장 가능성이 높아서 경력 개발에 좋은 환경입니다." };

		// 부정적인 리뷰를 나타내는 배열 ,
		String[] badReviews = { "연봉이 업계 평균에 미치지 못합니다.", "복지 혜택이 부족하고 개선이 필요합니다.", "회사 문화가 폐쇄적이어서 의사소통이 어렵습니다.",
				"야근이 잦으며 워라밸이 매우 나쁩니다.", "성장의 기회가 제한적이어서 경력 개발이 어렵습니다." };

		int[] numbers = {2375, 2408, 2241, 2105, 2191, 2303, 10138, 3861, 2969, 2402, 2799, 2823, 2707, 2937, 8527,
				3510, 8843, 2890, 2456, 5151, 3570, 9022, 8360, 3241, 3142, 3467, 8478, 3891, 8912, 4380, 7524, 5403,
				3464, 7831, 2499, 4184, 2827, 2298, 7717, 4524, 3944, 6410, 4082, 3096, 4656, 3258, 3838, 6664, 3912,
				9654, 6007, 2219, 2781, 7208, 3845, 4693, 6136, 10533, 5377, 2902, 4374, 5972, 8315, 4509, 3441, 4763,
				7310, 3600, 5370, 4586, 7711, 7802, 7917, 4238, 4358, 5258, 7669, 2930, 5466, 5998, 4664, 4492, 11413,
				8073, 5179, 4759, 7632, 9953, 3921, 3789, 4360, 5252, 5453, 8606, 8606, 5656, 5798, 4812, 3213, 4076,
				4432, 9619, 9591, 4813, 8141, 6251, 4575, 4575, 9157, 7858, 2944, 4865, 11478, 5644, 11852, 5094, 6028,
				4915, 4887, 10047, 7171, 10462, 3427, 6083, 5374, 3440, 4201, 9092, 3232, 4421, 4989, 10046, 6576, 9291,
				8016, 9383, 6317, 10971, 4391, 2874, 7149, 3143, 4779, 11768, 5826, 5333, 7723, 11661, 6414, 3419, 9831,
				4921, 4898, 4007, 5307, 9216, 4009, 6148, 3633, 8505, 2607, 8887, 11083, 5766, 4696, 5409, 3070, 5572,
				3484, 11261, 5742, 8722, 3739, 8162, 7357, 7123, 11185, 4764, 9315, 8248, 8643, 6962, 3322, 4146, 5321,
				7588, 8464 };
		// 랜덤 객체 생성
		Random random = new Random();

		Calendar c1 = Calendar.getInstance();

		c1.add(Calendar.MONTH, -4);

		Random rnd = new Random();
		// 70% 확률로 조작
		int[] seed = { 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 };
		// 2~5회 중 랜덤
		int[] seed2 = { 1, 2 };

		for (int i = 0; i < numbers.length; i++) {
			//int result = seed[rnd.nextInt(seed.length)];

			Calendar copy = (Calendar) c1.clone();

			//if (result == 1) {
				for (int j = 0; j < 6; j++) {
					try {
						String sql = "insert into tblcompanyreview values (seqcompanyreview.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'wantjob', 1)";

						pstat = conn.prepareStatement(sql);
						pstat.setInt(1, random.nextInt(4) + 2);
						pstat.setInt(2, random.nextInt(4) + 2);
						pstat.setInt(3, random.nextInt(4) + 2);
						pstat.setInt(4, random.nextInt(4) + 2);
						pstat.setInt(5, random.nextInt(4) + 2);
						pstat.setString(6, conclusions[random.nextInt(conclusions.length)]);
						pstat.setString(7, goodReviews[random.nextInt(goodReviews.length)]);
						pstat.setString(8, badReviews[random.nextInt(badReviews.length)]);
						pstat.setString(9, String.format("%tF", copy));
						// pstat.setInt(10, 5942);
						pstat.setInt(10, numbers[i]);

						pstat.executeUpdate();
						pstat.close();

					} catch (Exception e) {
						System.out.println("dummy.add");
						e.printStackTrace();
					}

					copy.add(Calendar.DATE, i * 3);
				}

				c1.add(Calendar.DATE, 1);
				// System.out.printf("%tF\r\n", c1);
			//}
		}
		System.out.println("완료");
	}

	private static void dummyStd() {
		String[] subjects = { "스터디", "모임", "취준", "프로젝트", "그룹" };
		String[] objects = { "새로운 멤버", "팀원", "동료", "참가자", "파트너" };
		String[] verbs = { "모집", "찾아요", "구해요", "구함" };
		int[] numbers = { 2375, 2408, 2241, 2105, 2191, 2303, 10138, 3861, 2969, 2402, 2799, 2823, 2707, 2937, 8527,
				3510, 8843, 2890, 2456, 5151, 3570, 9022, 8360, 3241, 3142, 3467, 8478, 3891, 8912, 4380, 7524, 5403,
				3464, 7831, 2499, 4184, 2827, 2298, 7717, 4524, 3944, 6410, 4082, 3096, 4656, 3258, 3838, 6664, 3912,
				9654, 6007, 2219, 2781, 7208, 3845, 4693, 6136, 10533, 5377, 2902, 4374, 5972, 8315, 4509, 3441, 4763,
				7310, 3600, 5370, 4586, 7711, 7802, 7917, 4238, 4358, 5258, 7669, 2930, 5466, 5998, 4664, 4492, 11413,
				8073, 5179, 4759, 7632, 9953, 3921, 3789, 4360, 5252, 5453, 8606, 8606, 5656, 5798, 4812, 3213, 4076,
				4432, 9619, 9591, 4813, 8141, 6251, 4575, 4575, 9157, 7858, 2944, 4865, 11478, 5644, 11852, 5094, 6028,
				4915, 4887, 10047, 7171, 10462, 3427, 6083, 5374, 3440, 4201, 9092, 3232, 4421, 4989, 10046, 6576, 9291,
				8016, 9383, 6317, 10971, 4391, 2874, 7149, 3143, 4779, 11768, 5826, 5333, 7723, 11661, 6414, 3419, 9831,
				4921, 4898, 4007, 5307, 9216, 4009, 6148, 3633, 8505, 2607, 8887, 11083, 5766, 4696, 5409, 3070, 5572,
				3484, 11261, 5742, 8722, 3739, 8162, 7357, 7123, 11185, 4764, 9315, 8248, 8643, 6962, 3322, 4146, 5321,
				7588, 8464 };

		StudyDAO dao = new StudyDAO();

		Calendar c1 = Calendar.getInstance();

		c1.add(Calendar.MONTH, -3);

		Random rnd = new Random();
		// 70% 확률로 조작
		int[] seed = { 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 };
		// 2~5회 중 랜덤
		int[] seed2 = { 2, 3 };

		while (!String.format("%tF", c1).equals("2024-05-06")) {
			int result = seed[rnd.nextInt(seed.length)];

			if (result == 1) {

				// 글 추가
				StudyDTO dto = new StudyDTO();
				dto.setStd_content("내용");
				dto.setId("hong123");

				Calendar copy = (Calendar) c1.clone();

				for (int i = 0; i < seed2[rnd.nextInt(seed2.length)]; i++) {
					String subject = subjects[rnd.nextInt(subjects.length)] + " " + objects[rnd.nextInt(objects.length)]
							+ " " + verbs[rnd.nextInt(verbs.length)];
					String cp_seq = numbers[rnd.nextInt(numbers.length)] + "";
					dto.setStd_title(subject);
					dto.setCp_seq(cp_seq);
					dto.setStd_regdate(String.format("%tF", copy));
					copy.add(Calendar.DATE, i * 3);
					dto.setStd_duedate(String.format("%tF", copy));

					dao.addStudy(dto);
				}
			}

			c1.add(Calendar.DATE, 1);
			// System.out.printf("%tF\r\n", c1);
		}
		System.out.println("완료");
	}
}
