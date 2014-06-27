<#include "head.ftl">

<body>
<style>
.form-horizontal .control-label {
  float: left;
  width: 160px;
  padding-top: 5px;
  text-align: right;
}
.form-horizontal .controls {
  *display: inline-block;
  *padding-left: 20px;
  margin-left: 180px;
  *margin-left: 0;
}
.bass_plan .bass_left,.bass_plan .bass_right{ float:left;}
.bass_plan li{ list-style:none;  position:relative; display:table; clear:both; height:35px;}
.bass_plan li label{ float:left; width:140px; text-align:right; display:inline-block; padding-right:5px; padding-top: 5px;}
.bass_plan li input{ float:left;}
</style>

<div class="container">
	<div>
		${token!}
	</div>
	<div>
	<a href="/report/gettradedata?nick=${nick!}">Get trade data now</a>
	</div>
	
	<div id="contentbox">
	<p>
		<div style="float:left; margin-right:10px;">ProductId: <input type='text' id='pid' value='${pid!}' style='width:200px'></div>
		<div style="float:left; margin-right:10px;">StoreId: <input type='text' id='sid' value='${sid!}' style='width:200px'></div>
		<button style="float:left; margin-right:10px;" class="btnSearch">查询</button>
	</p>
	
	<div id="table">
		<table class="bass_table" border=1 cellspacing=0 cellpadding=0>
			<tr style=" background:#000; color:#fff;">
				<td width=60>TradeId</td>
				<td width=60>OrderId</td>
				<td width=120>Status</td>
				<td width=100>Title</td>
				<td width=100>Price</td>
				<td width=80>Item</td>
				<td width=50>Number</td>
				<td width=100>Sku</td>
				<td width=120>Seller</td>
				<td width=80>Total Fee</td>
				<td width=80>Payment</td>
				<td width=80>Created</td>
				<td width=100>Modified</td>
				<td width=100>PayTime</td>
				<td width=100>EndTime</td>
			</tr>
			<#list tborders as o>
			<tr bgcolor="#ffffff">
				
				<td>${data.impression}</td>
				<td>${data.click}</td>
				<td>${data.cost}</td>
				<td>${data.conversion}</td>
				<td>${data.bidTry}</td>
				<td>${data.bidPrice}</td>
			</tr>
			</#list>
		</table>
	</div>
</div>

</body>
</html>