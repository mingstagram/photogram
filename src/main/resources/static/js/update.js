// (1) 회원정보 수정
function update(userId) {
	
	let data = $("#profileUpdate").serialize(); // id=profileUpdate안의 폼값들이 전부 담김
	
	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,
		data:data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res=>{
		alert("회원 수정 성공");
		location.href = `/user/${userId}`;
	}).fail(error=>{
		alert("회원 수정 실패")
		location.href = `/user/${userId}/update`;
	});
	
}