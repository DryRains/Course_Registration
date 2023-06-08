package Global;

public class Locale {
		
		public final static String OK_LABEL = "확인";
		public final static String CANCEL_LABEL = "취소";
		public final static String CONFIRM_LOGOUT = "로그아웃 하시겠습니까?";
		public final static String TITlE_CONFIRM_LOGOUT = "로그아웃 확인";
		
	public class CMainFrame{
		public final static String TITLE = "Registration System";
	}	
		
	public class LAccountPanel{
		//postfix - 접미사. prefix = "접두사"
		public final static String INSA_POSTFIX = "님 안녕하세요.";
		public final static String LOGIN_TIME_PREFIX = "로그인 시간은";
		public final static String TIME_FORMAT = "yyyy-MM-dd hh:mm";
		public final static String IPNIDA= " 입니다. ";
		public final static String LOGOUT = "로그아웃";
		public final static String VISIT_MJUWEB = "홈페이지";
		public final static String SCHEDULE = "학사일정";
		public final static String MYINFO = "나의정보";
		public final static String MJU_URL = "https://www.mju.ac.kr/mjukr/index.do";
	}
	


	public final static class LLoginPanel{
		public final static String ID_LABEL = "   ID";
		public final static String PASSWORD_LABEL = "   PASSWORD";
		public final static String LOGIN_BUTTON = "LOGIN";
		public final static String EXIT_BUTTON = "EXIT";
		
		public final static String LOGIN_ERROR = "로그인에 실패했습니다. 아이디와 비밀번호를 확인하세요.";
		public final static String LOGIN_SUCCESS_PREFIX = "로그인에 성공했습니다. 환영합니다 ";
		public final static String LOGIN_SUCCESS_POSTFIX = "님";
		public final static String DIALOG_ERROR = "Error!";
		public final static String DIALOG_SUCCESS = "Success!";
		
		public final static String LOGIN_LIMITED = "로그인에 5번 실패하셨습니다. 프로그램을  강제 종료합니다.";
	}
	
	public final static class LMyInfoDialog{
		public final static String TITLE = "학생 카드";
		public final static String BTN_REVISE = "수정";
		
		public final static String ID_IS = "학번 : ";
		public final static String NAME_IS = "이름 : ";
		public final static String DEPT_IS = "학과 : ";
		public final static String ADDRESS_IS = "주소 : ";
		public final static String EMAIL_IS = "이메일 : ";
		public final static String HP_IS = "HP : ";
		public final static String DEFAULT_IMAGE_LOCATION = "account/images/default.jpg";
		public final static String IMAGE_LOCATION = "account/images/";
		public final static String IMAGE_TYPE = ".jpg";
	}
	
	public final static class LReviseDialog{
		public final static String TITLE = "학생 정보 수정";
		public final static String CHANGE_SI = "1. 특별시/광역시/도를 입력하세요: ";
		public final static String CHANGE_GU = "2. (일반)시/구를 입력하세요: ";
		public final static String CHANGE_DONG = "3. 동/읍/면을 입력하세요: ";
		public final static String CHANGE_HO = "4. 호/번지를 입력하세요: ";
		public final static String CHANGE_EMAIL = "5. 이메일 주소를 입력하세요: ";
		public final static String CHANGE_PHONE = "6. 휴대폰 번호를 입력하세요: ";
		public final static String CONFIRM_SAVE = "수정한 정보를 저장하시겠습니까? (수정된 정보는 재로그인시 반영됩니다.)";
		public final static String TITLE_CONFIRM_SAVE = "변경 내용 저장";
	}
	
	public final static class LSignUpDialog{
		public final static String INPUT_ID = "1. 아이디(학번)을 입력하세요 : ";
		public final static String INPUT_PW = "2. 비밀번호를 입력하세요 : ";
		public final static String INPUT_NAME = "3. 이름을 입력하세요 : ";
		public final static String INPUT_DEPT = "4. 학과를 입력하세요 : ";
		public final static String INPUT_SI = "5. (광역)시/도를 입력하세요 : ";
		public final static String INPUT_GU = "6. (일반)시/구를 입력하세요 : ";
		public final static String INPUT_DONG = "7. 동/읍/면을 입력하세요 : ";
		public final static String INPUT_HO = "8. 호/번지를 입력하세요 : ";
		public final static String INPUT_EMAIL = "9. 이메일 주소를 입력하세요 : ";
		public final static String INPUT_PHONE = "10. 휴대폰 번호를 입력하세요 : ";
		
		public final static String SIGNUP = "회원 가입";
		
		public final static String COMPLETE_SIGNUP = "회원가입이 완료되었습니다. 등록된 아이디와 패스워드로 로그인해주세요";
		public final static String ID_ALREADY_EXITS = "이미 존재하는 아이디입니다.";
		public final static String ID_MUST_BE_EIGHT = "아이디는 8자리의 학번이어야 합니다. 다시 입력해주세요.";
		public final static String FILL_EVERY_BLANK = "입력하지 않은 항목이 존재합니다. 모든 칸을 채워주세요.";
		
	}
	

}
