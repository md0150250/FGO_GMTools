<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<script>
	window.onload = function() {
		

		var Account = document.getElementById('Account');
		var Mail = document.getElementById('Mail');
		var Bulletin = document.getElementById('Bulletin');
		var Machine = document.getElementById("Machine");
		//以下是玩家帳號
		var userID = 0;
		var mudcoderesult = document.getElementById('mudcoderesult');
		var goldresult = document.getElementById('goldresult');
		var LVresult = document.getElementById('LVresult');
		var EXPresult = document.getElementById('EXPresult');
		var vipLVresult = document.getElementById('vipLVresult');
		var vipEXPresult = document.getElementById('vipEXPresult');
		var TotalReserve = document.getElementById('TotalReserve');
		var ReserveHistory = document.getElementById('ReserveHistory');
		var EmailHistory = document.getElementById('EmailHistory');
		var TR = document.getElementById('TR');
		//以下是發送至系統信箱
		var x = 0;
		var mailSend = document.getElementById('mailSend');
		var mailLog = document.getElementById('mailLog');
		var sendMailHistory = document.getElementById('sendMailHistory');
		//以下是公告 
		var bulletinMessage = document.getElementById('bulletinMessage');
		var allBulletin = document.getElementById('allBulletin');
		//-----------------------------------------------------------
		//選擇玩家帳號
		var chooseAccount = document.getElementById('chooseAccount');
		chooseAccount.onclick = function() {
			chooseAccount.style.background = "white";
			chooseMail.style.background = "gray";
			chooseBulletin.style.background = "gray";
			chooseMachine.style.background = "gray";
			Account.style.display = "table";
			Mail.style.display = "none";
			Bulletin.style.display = "none";
			bulletinMessage.style.display = "none";
			Machine.style.display = "none";
		}
		//選擇發送至系統信箱
		var chooseMail = document.getElementById('chooseMail');
		chooseMail.onclick = function() {
			chooseAccount.style.background = "gray";
			chooseMail.style.background = "white";
			chooseBulletin.style.background = "gray";
			chooseMachine.style.background = "gray";
			Account.style.display = "none";
			Mail.style.display = "table";
			Bulletin.style.display = "none";
			bulletinMessage.style.display = "none";
			Machine.style.display = "none";
		}
		//選擇公告
		var chooseBulletin = document.getElementById('chooseBulletin');
		chooseBulletin.onclick = function() {
			chooseAccount.style.background = "gray";
			chooseMail.style.background = "gray";
			chooseBulletin.style.background = "white";
			chooseMachine.style.background = "gray";
			Account.style.display = "none";
			Mail.style.display = "none";
			Bulletin.style.display = "table";
			Machine.style.display = "none";
			
			//bulletinMessage.innerHTML = "";

			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "/FGO/findBulletinServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send();
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					m = result.message;
					if (m) {
						//先將全部公告清空再放入公告
						allBulletin.innerHTML = "";
						for (var i = 0; i < m.length; i++) {
							//one包含左半部(圖片)和右半部(值)
							one = document.createElement("div");
							one.style.height = "250px";
							one.style.border = 'solid 1px black';
							one.style.margin = "5px 0px ";
							//dimg為整個左半部(放入img)
							dimg = document.createElement("div");
							dimg.style.width = "400px";
							dimg.style.height = "250px";
							dimg.style.display = "inline-block";
							dimg.style.cssFloat = "left";
							dimg.style.background = "url(../img/"
									+ m[i].picturePath + ")";
							dimg.style.backgroundSize = "400px 250px";
							//dimg.innerHTML="<font>活動期間</font>"
							//<div style="width: 170px; height: 89px; background: url(图片地址);">
							//img = document.createElement("img");
							//img.src="../host-manager/img/"+m[i].picturePath;
							//img.style.height="250px";
							//img.style.width="380px";
							//dimg.appendChild(img);
							one.appendChild(dimg);
							//dform為整個右半部(放入form)
							dform = document.createElement("div");
							dform.style.width = "900px";
							dform.style.height = "200px";
							dform.style.display = "inline-block";
							//dform.style.border='solid 1px red';
							form = document.createElement("form");
							form.name = "itemForm" + i;
							form.method = "post";
							form.action = "/FGO/changeBulletinServlet"
							form.enctype = "multipart/form-data";
							form.style.width = "900px";
							form.style.height = "200px";
							//換行
							div = document.createElement("div");
							div.style.height = "30px";
							form.appendChild(div);
							//圖片id
							inputid = document.createElement("input");
							inputid.type = "text";
							inputid.name = "id" + i;
							inputid.value = m[i].id;
							inputid.style.display = "none";
							form.appendChild(inputid);
							//公告開始日期  
							fontbegin = document.createElement("font");
							fontbegin.size = "4px"
							fontbegin.innerHTML = "公告開始日期:";
							fontbegin.style.margin = "0px 5px 0px 0px";
							inputbegin = document.createElement("input");
							inputbegin.type = "text";
							inputbegin.name = "beginAt" + i;
							inputbegin.placeholder = m[i].beginAt;
							form.appendChild(fontbegin);
							form.appendChild(inputbegin);
							//公告截止日期
							fontend = document.createElement("font");
							fontend.size = "4px"
							fontend.innerHTML = "公告截止日期:";
							fontend.style.margin = "0px 5px 0px 20px";
							inputend = document.createElement("input");
							inputend.type = "text";
							inputend.name = "endAt" + i;
							inputend.placeholder = m[i].endAt;
							form.appendChild(fontend);
							form.appendChild(inputend);
							//優先序
							fontrank = document.createElement("font");
							fontrank.size = "4px"
							fontrank.innerHTML = "優先序";
							fontrank.style.margin = "0px 5px 0px 20px";
							inputrank = document.createElement("input");
							inputrank.type = "text";
							inputrank.name = "rank" + i;
							inputrank.placeholder = m[i].rank;
							form.appendChild(fontrank);
							form.appendChild(inputrank);
							//換行
							div = document.createElement("div");
							div.style.height = "30px";
							form.appendChild(div);
							//圖片
							inputpicture = document.createElement("input");
							inputpicture.type = "file";
							inputpicture.name = "picturePath" + i;
							form.appendChild(inputpicture);
							//上傳
							input = document.createElement("input");
							input.type = "submit";
							input.value = "上傳";
							input.onclick = a;
							form.appendChild(input);

							dform.appendChild(form);
							inputdelete = document.createElement("input");
							inputdelete.id = "delete" + m[i].id;
							inputdelete.type = "button";
							inputdelete.value = "刪除";
							dform.appendChild(inputdelete);
							one.appendChild(dform);
							allBulletin.appendChild(one);
							//alert(m[i].id)
						}
						//---------------------------------
						for (var i = 0; i < m.length; i++) {
							document
									.getElementById("delete" + m[i].id)
									.addEventListener(
											"click",
											function() {
												//bulletinMessage.style.display = "block";
												var choose = confirm("是否要刪除公告");
												var id = this.id
                                                
												var xhr1 = new XMLHttpRequest();
												xhr1
														.open(
																"POST",
																"/FGO/deleteBulletinServlet",
																true);
												xhr1
														.setRequestHeader(
																"Content-Type",
																"application/x-www-form-urlencoded");
												xhr1.send("id=" + id  
														+ "&choose=" + choose);
												xhr1.onreadystatechange = function() {
													if (xhr1.readyState == 4
															&& xhr1.status == 200) {
														result = JSON.parse(xhr1.responseText);
														bulletinMessage.style.display = "block";
														if(result.Message==1){
															bulletinMessage.innerHTML="刪除成功";
														}else{
															bulletinMessage.innerHTML="刪除失敗";
														}
														
													}
												}
											})
						}
						//---------------------------------
					}
				}
			}
		}

		function a() {
			return confirm('確認要更改公告？');
		}

		//選擇機台現況	
		var chooseMachine = document.getElementById('chooseMachine');
		chooseMachine.onclick = function() {
			chooseAccount.style.background = "gray";
			chooseMail.style.background = "gray";
			chooseBulletin.style.background = "gray";
			chooseMachine.style.background = "white";
			Account.style.display = "none";
			Mail.style.display = "none";
			Bulletin.style.display = "none";
			bulletinMessage.style.display = "none";
			Machine.style.display = "table";
		}

		//查詢帳號按鈕
		var accountbt = document.getElementById("accountbt");
		accountbt.onclick = function() {
			var account = document.getElementById("account").value;
			var mudcode = document.getElementById('mudcode');
			var gold = document.getElementById('gold');
			var LV = document.getElementById('LV');
			var EXP = document.getElementById('EXP');
			var vipLV = document.getElementById('vipLV');
			var vipEXP = document.getElementById('vipEXP');

			if (account == "") {
				alert("未輸入帳號");
			}

			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "/FGO/findAccountServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("account=" + account);
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					//先將成功失敗清空
					mudcoderesult.innerHTML = "";
					goldresult.innerHTML = "";
					LVresult.innerHTML = "";
					EXPresult.innerHTML = "";
					vipLVresult.innerHTML = "";
					vipEXPresult.innerHTML = "";
					//先將ReserveHistory清空
					ReserveHistory.innerHTML = '<tr><td style="width: 10%;">日期</td><td style="width: 10%;">平台</td><td style="width: 20%;">儲值金額</td></tr>';
					//先將EmailHistory清空
					EmailHistory.innerHTML = '<tr><td style="width: 10%;">日期</td>	<td style="width: 10%;">平台</td>	<td style="width: 40%;">內容</td>	</tr>';
					if (result.userid) {
						userID = result.userid;
						mudcode.value = result.mudCode;
						gold.value = result.gold;
						LV.value = result.lv;
						EXP.value = result.exp;
						vipLV.value = result.vip;
						vipEXP.value = result.vipExp;
						TotalReserve.innerHTML = result.totalReserve;
						if (result.rblist) {
							for (var i = 0; i < (result.rblist).length; i++) {
								tr = document.createElement("tr");
								td1 = document.createElement("td");
								td1.innerHTML = result.rblist[i].day
								tr.appendChild(td1);
								td2 = document.createElement("td");
								td2.innerHTML = result.rblist[i].cellphone
								tr.appendChild(td2);
								td3 = document.createElement("td");
								td3.innerHTML = result.rblist[i].gold
								tr.appendChild(td3);
								ReserveHistory.appendChild(tr);
							}
						}

						if (result.datelist) {
							for (var i = 0; i < (result.datelist).length; i++) {
								tr = document.createElement("tr");
								td1 = document.createElement("td");
								td1.innerHTML = result.datelist[i]
								tr.appendChild(td1);
								td2 = document.createElement("td");
								td2.innerHTML = "Android"
								tr.appendChild(td2);
								td3 = document.createElement("td");
								td3.innerHTML = result.contentlist[i]
								tr.appendChild(td3);
								EmailHistory.appendChild(tr);
							}
						}

					} else {
						alert("查無此帳號");
						userID = 0;
						mudcode.value = 0;
						gold.value = 0;
						LV.value = 0;
						EXP.value = 0;
						vipLV.value = 0;
						vipEXP.value = 0;
						TotalReserve.innerHTML = "$$$";
					}

				}
			}
		}

		//修改泥碼按鈕
		var mudcodebt = document.getElementById("mudcodebt");
		mudcodebt.onclick = function() {
			var mudcode = document.getElementById('mudcode').value;
			var choose = confirm("將泥碼修改為:" + mudcode);
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "/FGO/changeAccountDataServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("userID=" + userID + "&mudcode=" + mudcode + "&choose="
					+ choose);
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					//先將成功或失敗全部清空
					mudcoderesult.innerHTML = "";
					goldresult.innerHTML = "";
					LVresult.innerHTML = "";
					EXPresult.innerHTML = "";
					vipLVresult.innerHTML = "";
					vipEXPresult.innerHTML = "";
					if (result.success == 2) {
						alert("沒有指定帳號");
					} else if (result.success == 0) {
						mudcoderesult.innerHTML = "<font color='red' size='4'>修改失敗</font>";
					} else if (result.success == 1) {
						mudcoderesult.innerHTML = "<font color='green' size='4'>修改成功</font>";
					}
				}
			}
		}

		//修改現金碼按鈕
		var goldbt = document.getElementById("goldbt");
		goldbt.onclick = function() {
			var gold = document.getElementById('gold').value;
			var choose = confirm("將現金碼修改為:" + gold);
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "/FGO/changeAccountDataServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("userID=" + userID + "&gold=" + gold + "&choose="
					+ choose);
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					//先將成功或失敗全部清空
					mudcoderesult.innerHTML = "";
					goldresult.innerHTML = "";
					LVresult.innerHTML = "";
					EXPresult.innerHTML = "";
					vipLVresult.innerHTML = "";
					vipEXPresult.innerHTML = "";
					if (result.success == 2) {
						alert("沒有指定帳號");
					} else if (result.success == 0) {
						goldresult.innerHTML = "<font color='red' size='4'>修改失敗</font>";
					} else if (result.success == 1) {
						goldresult.innerHTML = "<font color='green' size='4'>修改成功</font>";
					}
				}
			}
		}

		//修改LV按鈕
		var LVbt = document.getElementById("LVbt");
		LVbt.onclick = function() {
			var LV = document.getElementById('LV').value;
			var choose = confirm("將LV修改為:" + LV);
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "/FGO/changeAccountDataServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("userID=" + userID + "&LV=" + LV + "&choose=" + choose);
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					//先將成功或失敗全部清空
					mudcoderesult.innerHTML = "";
					goldresult.innerHTML = "";
					LVresult.innerHTML = "";
					EXPresult.innerHTML = "";
					vipLVresult.innerHTML = "";
					vipEXPresult.innerHTML = "";
					if (result.success == 2) {
						alert("沒有指定帳號");
					} else if (result.success == 0) {
						LVresult.innerHTML = "<font color='red' size='4'>修改失敗</font>";
					} else if (result.success == 1) {
						LVresult.innerHTML = "<font color='green' size='4'>修改成功</font>";
					}
				}
			}
		}

		//修改EXP按鈕
		var EXPbt = document.getElementById("EXPbt");
		EXPbt.onclick = function() {
			var EXP = document.getElementById('EXP').value;
			var choose = confirm("將EXP修改為:" + EXP);
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "/FGO/changeAccountDataServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("userID=" + userID + "&EXP=" + EXP + "&choose=" + choose);
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					//先將成功或失敗全部清空
					mudcoderesult.innerHTML = "";
					goldresult.innerHTML = "";
					LVresult.innerHTML = "";
					EXPresult.innerHTML = "";
					vipLVresult.innerHTML = "";
					vipEXPresult.innerHTML = "";
					if (result.success == 2) {
						alert("沒有指定帳號");
					} else if (result.success == 0) {
						EXPresult.innerHTML = "<font color='red' size='4'>修改失敗</font>";
					} else if (result.success == 1) {
						EXPresult.innerHTML = "<font color='green' size='4'>修改成功</font>";
					}
				}
			}
		}

		//修改vipLV(=VIP)按鈕
		var vipLVbt = document.getElementById("vipLVbt");
		vipLVbt.onclick = function() {
			var vipLV = document.getElementById('vipLV').value;
			var choose = confirm("將vipLV修改為:" + vipLV);
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "/FGO/changeAccountDataServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("userID=" + userID + "&vipLV=" + vipLV + "&choose="
					+ choose);
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					//先將成功或失敗全部清空
					mudcoderesult.innerHTML = "";
					goldresult.innerHTML = "";
					LVresult.innerHTML = "";
					EXPresult.innerHTML = "";
					vipLVresult.innerHTML = "";
					vipEXPresult.innerHTML = "";
					if (result.success == 2) {
						alert("沒有指定帳號");
					} else if (result.success == 0) {
						vipLVresult.innerHTML = "<font color='red' size='4'>修改失敗</font>";
					} else if (result.success == 1) {
						vipLVresult.innerHTML = "<font color='green' size='4'>修改成功</font>";
					}
				}
			}
		}

		//修改vipEXP按鈕
		var vipEXPbt = document.getElementById("vipEXPbt");
		vipEXPbt.onclick = function() {
			var vipEXP = document.getElementById('vipEXP').value;
			var choose = confirm("將vipEXP修改為:" + vipEXP);
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "/FGO/changeAccountDataServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("userID=" + userID + "&vipEXP=" + vipEXP + "&choose="
					+ choose);
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					//先將成功或失敗全部清空
					mudcoderesult.innerHTML = "";
					goldresult.innerHTML = "";
					LVresult.innerHTML = "";
					EXPresult.innerHTML = "";
					vipLVresult.innerHTML = "";
					vipEXPresult.innerHTML = "";
					if (result.success == 2) {
						alert("沒有指定帳號");
					} else if (result.success == 0) {
						vipEXPresult.innerHTML = "<font color='red' size='4'>修改失敗</font>";
					} else if (result.success == 1) {
						vipEXPresult.innerHTML = "<font color='green' size='4'>修改成功</font>";
					}
				}
			}
		}

		//重整按鈕
		var reloadbt = document.getElementById("reloadbt");
		reloadbt.onclick = function() {
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "/FGO/findAccountServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send();
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					//將全部清空
					userID = 0;
					document.getElementById("account").value = "";
					document.getElementById("mudcode").value = "";
					document.getElementById("gold").value = "";
					document.getElementById("LV").value = "";
					document.getElementById("EXP").value = "";
					document.getElementById("vipLV").value = "";
					document.getElementById("vipEXP").value = "";
					mudcoderesult.innerHTML = "";
					goldresult.innerHTML = "";
					LVresult.innerHTML = "";
					EXPresult.innerHTML = "";
					vipLVresult.innerHTML = "";
					vipEXPresult.innerHTML = "";
					TotalReserve.innerHTML = "$$$";
					ReserveHistory.innerHTML = "<tr><td style='width: 10%;'>日期</td><td style='width: 10%;'>平台</td><td style='width: 20%;'>儲值金額</td>	</tr>";
					EmailHistory.innerHTML = '<tr><td style="width: 10%;">日期</td>	<td style="width: 10%;">平台</td>	<td style="width: 40%;">內容</td>	</tr>';
				}
			}
		}

		//儲值歷程
		var Reserve = document.getElementById('Reserve');
		Reserve.onclick = function() {
			Email.style.background = "gray";
			Reserve.style.background = "white";
			TR.style.visibility = "visible";
			ReserveHistory.style.display = "table";
			EmailHistory.style.display = "none";
		}
		//信箱歷程
		var Email = document.getElementById('Email');
		Email.onclick = function() {
			Email.style.background = "white";
			Reserve.style.background = "gray";
			TR.style.visibility = "hidden";
			ReserveHistory.style.display = "none";
			EmailHistory.style.display = "table";
		}

		//發送至系統信箱
		//選擇發送
		var chooseMailSend = document.getElementById('chooseMailSend');
		chooseMailSend.onclick = function() {
			chooseMailSend.style.background = "white";
			chooseMailLog.style.background = "gray";
			mailSend.style.display = "table";
			mailLog.style.display = "none";
		}

		//選擇紀錄
		var chooseMailLog = document.getElementById('chooseMailLog');
		chooseMailLog.onclick = function() {
			chooseMailSend.style.background = "gray";
			chooseMailLog.style.background = "white";
			mailSend.style.display = "none";
			mailLog.style.display = "block";
			//先將資料清空
			sendMailHistory.innerHTML = '<tr><td style="width: 160px;">日期</td><td style="width: 300px;">對象</td><td style="width: 500px;">內容</td><td style="width: 180px;">發布者</td></tr>';
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "/FGO/findMailServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send();
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					message = result.message2;
					userid = result.message1;
					x = message.length;
					for (i = 0; i < message.length; i++) {
						tr = document.createElement("tr");
						td1 = document.createElement("td");
						td1.innerHTML = message[i].date;
						//td1.style.width="170px";
						tr.appendChild(td1);

						td2 = document.createElement("td");
						td2.innerHTML = message[i].userID;
						//td1.style.width="300px";
						td2.id = "tr" + (i + 1);
						tr.appendChild(td2);

						td3 = document.createElement("td");
						td3.innerHTML = message[i].message;
						tr.appendChild(td3);
						td4 = document.createElement("td");
						td4.innerHTML = message[i].gm;
						tr.appendChild(td4);
						sendMailHistory.appendChild(tr);

						tr1 = document.createElement("tr");
						td5 = document.createElement("td");

						tr1.appendChild(td5);
						div = document.createElement("td");
						div.id = "div" + (i + 1);
						div.innerHTML = userid[i].userID;
						div.style.display = "none";
						div.style.width = "300px";
						div.style.height = "200px";
						div.style.border = "solid";
						div.style.margin = "0px 0px 0px 0px";
						div.style.overflow = "scroll";
						tr1.appendChild(div);
						sendMailHistory.appendChild(tr1);
					}
					//點一 下打開並將其他關起來
					for (i = 0; i < message.length; i++) {
						document
								.getElementById("tr" + (i + 1))
								.addEventListener(
										"click",
										function() {
											var s = this.id;
											var array = s.split("r");
											var number = array[1];
											for (j = 0; j < message.length; j++) {
												document.getElementById("div"
														+ (j + 1)).style.display = "none";
											}
											document.getElementById("div"
													+ (number)).style.display = "inline-block";

										});
					}
					//點兩 下關起來	
					for (i = 0; i < message.length; i++) {
						document
								.getElementById("tr" + (i + 1))
								.addEventListener(
										"dblclick",
										function() {
											var s = this.id;
											var array = s.split("r");
											var number = array[1];
											for (j = 0; j < message.length; j++) {
												document.getElementById("div"
														+ (number)).style.display = "none";
											}

										});
					}

				}
			}

		}

		//發送信件
		var send = document.getElementById('send');
		send.onclick = function() {
			var msAccount = document.getElementById('mailSendAccount').value;
			var msGold = document.getElementById('mailSendGold').value;
			var msMudCode = document.getElementById('mailSendMudCode').value;
			var msLVexp = document.getElementById('mailSendLVexp').value;
			var msVIPexp = document.getElementById('mailSendVIPexp').value;
			var msLotteryTicket = document
					.getElementById('mailSendLotteryTicket').value;
			var msMessage = document.getElementById('mailSendMessage').value;
			var choose = confirm("是否要發送信件");
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "/FGO/sendMailServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("account=" + msAccount + "&gold=" + msGold + "&mudCode="
					+ msMudCode + "&lvExp=" + msLVexp + "&vipExp=" + msVIPexp
					+ "&lotteryTicket=" + msLotteryTicket + "&message="
					+ msMessage + "&all=false" + "&choose=" + choose);
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					message = result.message
					if (message == 0) {
						alert("有欄位未填寫");
					} else if (message == 4) {
						alert("輸入的帳號有錯誤");
					} else if (message == 2) {
						alert("輸入格式不對");
					} else if (message == 3) {
						alert("sql錯誤");
					} else if (message == 1) {
						alert("寄信成功");
						
						document.getElementById('mailSendAccount').value = "";
						document.getElementById('mailSendGold').value = "";
						document.getElementById('mailSendMudCode').value = "";
						document.getElementById('mailSendLVexp').value = "";
						document.getElementById('mailSendVIPexp').value = "";
						msLotteryTicket = document
								.getElementById('mailSendLotteryTicket').value = "";
						document.getElementById('mailSendMessage').value = "";
					}
				}
			}

		}

		//發送全服信件
		var sendAll = document.getElementById('sendAll');
		sendAll.onclick = function() {
			var msAccount = document.getElementById('mailSendAccount').value;
			var msGold = document.getElementById('mailSendGold').value;
			var msMudCode = document.getElementById('mailSendMudCode').value;
			var msLVexp = document.getElementById('mailSendLVexp').value;
			var msVIPexp = document.getElementById('mailSendVIPexp').value;
			var msLotteryTicket = document
					.getElementById('mailSendLotteryTicket').value;
			var msMessage = document.getElementById('mailSendMessage').value;
			var choose = confirm("是否要發送全服信件");

			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "/FGO/sendMailServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("account=" + msAccount + "&gold=" + msGold + "&mudCode="
					+ msMudCode + "&lvExp=" + msLVexp + "&vipExp=" + msVIPexp
					+ "&lotteryTicket=" + msLotteryTicket + "&message="
					+ msMessage + "&all=true" + "&choose=" + choose);
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					message = result.message
					if (message == 0) {
						alert("有欄位未填寫");
					} else if (message == 4) {
						alert("輸入的帳號有錯誤");
					} else if (message == 2) {
						alert("輸入格式不對");
					} else if (message == 3) {
						alert("sql錯誤");
					} else if (message == 1) {
						alert("寄信成功");
						
						document.getElementById('mailSendAccount').value = "";
						document.getElementById('mailSendGold').value = "";
						document.getElementById('mailSendMudCode').value = "";
						document.getElementById('mailSendLVexp').value = "";
						document.getElementById('mailSendVIPexp').value = "";
						msLotteryTicket = document
								.getElementById('mailSendLotteryTicket').value = "";
						document.getElementById('mailSendMessage').value = "";
					}
				}
			}
		}

		

	}
</script>
<body>
	<div style="height: 60px;">
		<div id="chooseAccount" align="center"
			style="display: inline-table; width: 15%; height: 40px; background-color: white;">
			<p>玩家帳號</p>
		</div>
		<div id="chooseMail" align="center"
			style="display: inline-table; width: 15%; height: 40px; background-color: gray;">
			<p>發送至系統信箱</p>
		</div>
		<div id="chooseBulletin" align="center"
			style="display: inline-table; width: 15%; height: 40px; background-color: gray;">
			<p>公告</p>
		</div>
		<div id="chooseMachine" align="center"
			style="display: inline-table; width: 15%; height: 40px; background-color: gray;">
			<p>機台現況</p>
		</div>
		<div align="center"
			style="display: inline-table; width: 15%; height: 40px; background-color: white;">
			<p>目前使用帳號:${sessionScope.gm.gmEmail}</p>
		</div>
	</div>

	<div id="Account"
		style="background-color: gray; width: 1600px; height: 800px; padding: 5px; border-style: solid;">
		<!--玩家帳號左半部 -->
		<div style="display: inline-table; width: 30%;">
			<div style="margin: 15px 0px;">
				<div style="display: inline-table;">
					<div
						style="text-align: center; background-color: gray; width: 100px; display: inline-table; font-size: 25px;">帳號:</div>
					<input type="text" name="account" id='account'
						value="${LoginOK.email}" placeholder="(fb,uid,gid)"
						style="border-style: solid; height: 25px;"><br>
				</div>
				<input type="button" value="搜尋" id="accountbt" style="height: 30px;">
				<input type="button" value="重整" id="reloadbt"
					style="height: 30px; margin-right: 50px">
			</div>
			<div style="margin: 15px 0px;">
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 100px; display: inline-table; font-size: 25px;">泥碼:</div>
					<input type="text" name="mudcode" id='mudcode'
						value="${LoginOK.email}" placeholder="0"
						style="border-style: solid; height: 25px;"><br>
				</div>
				<input type="button" value="修改" id="mudcodebt" style="height: 30px;">
				<div id="mudcoderesult" style="display: inline-table"></div>
			</div>
			<div style="margin: 15px 0px;">
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 100px; display: inline-table; font-size: 25px;">現金碼:</div>
					<input type="text" name="gold" id='gold' value="${LoginOK.email}"
						placeholder="0" style="border-style: solid; height: 25px;"><br>
				</div>
				<input type="button" value="修改" id="goldbt" style="height: 30px;">
				<div id="goldresult" style="display: inline-table"></div>
			</div>
			<div style="margin: 15px 0px;">
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 100px; display: inline-table; font-size: 25px;">帳號LV:</div>
					<input type="text" name="LV" id='LV' value="${LoginOK.email}"
						placeholder="0" style="border-style: solid; height: 25px;"><br>
				</div>
				<input type="button" value="修改" id="LVbt" style="height: 30px;">
				<div id="LVresult" style="display: inline-table"></div>
			</div>
			<div style="margin: 15px 0px;">
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 100px; display: inline-table; font-size: 25px;">EXP:</div>
					<input type="text" name="EXP" id='EXP' value="${LoginOK.email}"
						placeholder="0" style="border-style: solid; height: 25px;"><br>
				</div>
				<input type="button" value="修改" id="EXPbt" style="height: 30px;">
				<div id="EXPresult" style="display: inline-table"></div>
			</div>
			<div style="margin: 15px 0px;">
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 100px; display: inline-table; font-size: 25px;">vipLV:</div>
					<input type="text" name="vipLV" id='vipLV' value="${LoginOK.email}"
						placeholder="0" style="border-style: solid; height: 25px;"><br>
				</div>
				<input type="button" value="修改" id="vipLVbt" style="height: 30px;">
				<div id="vipLVresult" style="display: inline-table"></div>
			</div>
			<div style="margin: 15px 0px;">
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 100px; display: inline-table; font-size: 25px;">vipEXP:</div>
					<input type="text" name="vipEXP" id='vipEXP'
						value="${LoginOK.email}" placeholder="0"
						style="border-style: solid; height: 25px;"><br>
				</div>
				<input type="button" value="修改" id="vipEXPbt" style="height: 30px;">
				<div id="vipEXPresult" style="display: inline-table"></div>
			</div>
		</div>
		<!--玩家帳號右半部 -->
		<div
			style="display: inline-table; margin: 0px 5%; width: 55%; height: 90%;">
			<div id="Reserve"
				style="display: inline-table; background-color: white; text-align: center; font-size: 25px; width: 200px; border: 3px solid;">儲值歷程</div>
			<div id="Email"
				style="display: inline-table; background-color: gray; text-align: center; margin-left: 25px; font-size: 25px; width: 200px; border: 3px solid;">玩家信箱歷程</div>
			<br>
			<div id="TR">
				<div style="display: inline-table; font-size: 25px;">儲值總金額:</div>
				<div id="TotalReserve"
					style="display: inline-table; font-size: 25px; margin-left: 3%">$$$</div>
			</div>
			<div
				style="width: 80%; height: 80%; border-style: solid; overflow-y: scroll;">
				<table id="ReserveHistory">
					<tr>
						<td style="width: 10%;">日期</td>
						<td style="width: 10%;">平台</td>
						<td style="width: 20%;">儲值金額</td>
					</tr>
				</table>
				<table id="EmailHistory" style="display: none;">
					<tr>
						<td style="width: 10%;">日期</td>
						<td style="width: 10%;">平台</td>
						<td style="width: 40%;">內容</td>
					</tr>
				</table>

			</div>


		</div>
	</div>
	<div id="Mail"
		style="background-color: gray; width: 1600px; height: 800px; padding: 5px; border-style: solid; display: none;">
		<div id="chooseMailSend" align="center"
			style="display: inline-table; width: 10%; height: 20px; background-color: white; border: 3px solid;">
			<p>發送</p>
		</div>
		<div id="chooseMailLog" align="center"
			style="display: inline-table; width: 10%; height: 20px; background-color: gray; border: 3px solid; margin: 0px 5px">
			<p>紀錄</p>
		</div>
		<div id="mailSend"
			style="background-color: gray; width: 1500px; height: 650px; margin: 50px 25px;">
			<p style="margin-top: 50px; margin-bottom: 5px; font-size: 20px;">帳號:</p>
			<div style="border-style: solid;">
				<textarea id="mailSendAccount" rows="10" cols="210"
					style="border-style: solid;">

            </textarea>
			</div>

			<div style="margin-top: 10px; display: inline-table;">
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 100px; display: inline-table; font-size: 25px;">現金碼:</div>
					<input type="text" name="mailSendGold" id="mailSendGold"
						value="${LoginOK.email}" placeholder="0"
						style="border-style: solid; height: 25px;"><br>
				</div>
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 100px; display: inline-table; font-size: 25px;">泥碼:</div>
					<input type="text" name="mailSendMudCode" id='mailSendMudCode'
						value="${LoginOK.email}" placeholder="0"
						style="border-style: solid; height: 25px;"><br>
				</div>
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 100px; display: inline-table; font-size: 25px;">LVexp:</div>
					<input type="text" name="mailSendLVexp" id='mailSendLVexp'
						value="${LoginOK.email}" placeholder="0"
						style="border-style: solid; height: 25px;"><br>
				</div>
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 100px; display: inline-table; font-size: 25px;">VIPexp:</div>
					<input type="text" name="mailSendVIPexp" id='mailSendVIPexp'
						value="${LoginOK.email}" placeholder="0"
						style="border-style: solid; height: 25px;"><br>
				</div>
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 100px; display: inline-table; font-size: 25px;">彩票數:</div>
					<input type="text" name="mailSendLotteryTicket"
						id='mailSendLotteryTicket' value="${LoginOK.email}"
						placeholder="0" style="border-style: solid; height: 25px;"><br>
				</div>
				<div style="margin-top: 50px; padding-left: 35%">
					<input type="button" value="發送" id="send"
						style="height: 50px; width: 200px; margin-right: 10%"> <input
						type="button" value="全服發送" id="sendAll"
						style="height: 50px; width: 200px;">
				</div>
			</div>
			<p style="margin-top: 50px; margin-bottom: 5px; font-size: 20px;">文字訊息:</p>
			<div style="border-style: solid; width: 1500px;">

				<textarea id="mailSendMessage" rows="10" cols="210"
					style="border-style: solid;">

            </textarea>
			</div>

		</div>
		<div id="mailLog"
			style="background-color: gray; width: 90%; height: 600px; margin: 50px 50px; border-style: solid; display: none; overflow-y: scroll;">
			<table id="sendMailHistory">
				<tr>
					<td style="width: 160px;">日期</td>
					<td style="width: 300px;">對象</td>
					<td style="width: 500px;">內容</td>
					<td style="width: 180px;">發布者</td>
				</tr>
			</table>

		</div>



	</div>
	<div id="Bulletin"
		style="background-color: gray; width: 1600px; height: 800px; padding: 5px; border-style: solid; display: none;">
		<div align="center"
			style="display: inline-table; width: 100%; height: 40px; padding-top: 20px;">
			<font id="bulletinMessage" color='red'>${Message}</font>
		</div>
		<div style="height: 750px;">
			<!-- ... 這裡是註解文字 ................................................ -->
			<form name="itemForm" target="_self" id="itemForm" method="post"
				action="/FGO/insertBulletinServlet" enctype="multipart/form-data">
				<div style="display: inline-table">

					<div
						style="text-align: center; background-color: gray; width: 120px; display: inline-table; font-size: 20px;">公告開始日:</div>
					<input type="text" name="beginAt" id='beginAt'
						value="${LoginOK.email}" placeholder="yyyy/mm/dd"
						style="border-style: solid; height: 25px;"><br>
				</div>
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 120px; display: inline-table; font-size: 20px;">公告截止日:</div>
					<input type="text" name="endAt" id='endAt' value="${LoginOK.email}"
						placeholder="yyyy/mm/dd"
						style="border-style: solid; height: 25px;"><br>
				</div>
				<div style="display: inline-table">
					<div
						style="text-align: center; background-color: gray; width: 80px; display: inline-table; font-size: 20px;">優先序:</div>
					<input type="text" name="rank" id='rank' value="${LoginOK.email}"
						placeholder="0"
						style="border-style: solid; height: 25px; width: 60px"><br>
				</div>
				<div style="display: inline-table">

					<input type="file" name="picturePath" id="picturePath" size="40" />

				</div>
				<div style="display: inline-table">


					<input type="submit" value="上傳" id="insert"
						onclick="return(confirm('確認要新增公告？'))"
						style="height: 30px; width: 80px; margin-right: 10%;">
				</div>

			</form>
			<!-- ... 這裡是註解文字 ................................................ -->
			<div
				style="width: 90%; height: 90%; border-style: solid; margin-top: 30px; overflow-y: scroll;">
				<div id="allBulletin" style="margin: 20px"></div>
			</div>

		</div>
	</div>
	<div id="Machine"
		style="background-color: gray; width: 1600px; height: 800px; padding: 5px; border-style: solid; display: none;">

	</div>
	

</body>
</html>
