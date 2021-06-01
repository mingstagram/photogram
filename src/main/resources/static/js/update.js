// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault(); // 폼태그 액션을 막기!
	let data = $("#profileUpdate").serialize(); // id=profileUpdate안의 폼값들이 전부 담김
	
	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,
		data:data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res=>{ // HttpStatus 상태코드 200번대
		alert("회원 수정 성공");
		location.href = `/user/${userId}`;
	}).fail(error=>{ // HttpStatus 상태코드 200번대가 아닐 때
		if(error.errorMap == null){
			alert(error.responseJSON.message);
		} else {
			alert(JSON.stringify(error.responseJSON.errorMap));	
		}
		location.href = `/user/${userId}/update`;
	});
	
}