

let addFriend = (e) => {
	e.preventDefault();
	
	let jsonData = {
		userId: $("input[name=userId]").val(),
		userName: $("input[name=userName]").val(),
	}
	
	$.ajax({
		type: "POST",
		url: "/chatApp/api/addFriend",
		data: JSON.stringify(jsonData),
		contentType: "application/json",
		scriptCharset: "utf-8",
	}).then((result) => {
		if(is_json(result)){
			let json = JSON.parse(result);
			showFriends(json);
			$("input[name=userId]").val("");
			$("input[name=userName]").val("");
			alert("フレンドを追加しました。");
		}else{
			$("input[name=userId]").val("");
			$("input[name=userName]").val("");
			alert(result);
		}

	}, () =>{
		console.error("Error: ajax通信に失敗しました。");
	});
}

let showFriends = (json) => {
	let table = "";
	json.forEach((j) => {
		table += `<tr><td>${j.userId}</td><td>${j.userName}</td><td><form action="/chatApp/talkRoom" method="post"><input type="hidden" name="friendId" value="${j.userId}"><button type="submit" class="button01">トークルームへ</button></form></td></tr>`;
	})
	
	$("#friendList").empty();
	$("#friendList").append(table);
	
}

let is_json = (data) => {
	try {
		JSON.parse(data);
	} catch (error) {
		return false;
	}
	return true;
}