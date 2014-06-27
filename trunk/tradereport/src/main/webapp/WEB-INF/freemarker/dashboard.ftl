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
		<#include "token.ftl">
	</div>
	<div>
	<li><a href="/report/gettradedata?nick=${nick!}">Get trade data now</a></li>
	<li><a href="/report/getincrementtradedata?nick=${nick!}">Get increment trade data now</a></li>
	<li><a href="/report/seller">View all seller</a></li>
	</div>
	
	<div id="contentbox">
	<p>
	<form action="/report/dashboard" method="post">
		<div style="float:left; margin-right:10px;">Trade Id: <input type='text' id='tradeId' value='' style='width:200px'></div>
		<div style="float:left; margin-right:10px;">Order Id: <input type='text' id='orderId' value='' style='width:200px'></div>
		<div style="float:left; margin-right:10px;">Title: <input type='text' id='title' value='' style='width:300px'></div>
		<!--button style="float:left; margin-right:10px;" class="btnSearch">查询</button-->
		<input type="submit">查询</input>
	</form>
	</p>
	
	<div class="bass_plan">
		<table class="bass_table" border=1 cellspacing=0 cellpadding=0>
			<tr style=" background:#000; color:#fff;">
				<td width=150>Seller</td>
				<td width=100>TradeId</td>
				<td width=100>OrderId</td>
				<td width=100>Status</td>
				<td width=200>Title</td>
				<td width=80>Price</td>
				<td width=100>Item</td>
				<td width=50>Number</td>
				<td width=100>Sku</td>
				<td width=80>Total Fee</td>
				<td width=80>Payment</td>
				<td width=80>Created</td>
				<td width=100>Modified</td>
				<td width=100>PayTime</td>
				<td width=100>EndTime</td>
			</tr>
			<#if tborders?exists>
			<#list tborders as o>
			<tr bgcolor="#ffffff">
				<td>${o.sellerNick}</td>
				<td>${o.tradeId?c}</td>
				<td>${o.orderId?c}</td>
				<td>${o.status}</td>
				<td>${o.title}</td>
				<td>${o.price}</td>
				<td>${o.itemId?c}</td>
				<td>${o.number}</td>
				<td>${o.skuId}</td>
				<td>${o.totalFee}</td>
				<td>${o.payment!}</td>
				<td>${o.created}</td>
				<td>${o.modified!}</td>
				<td>${o.payTime!}</td>
				<td>${o.endTime!}</td>
			</tr>
			</#list>
			<#else>
			<tr>
				<td colspan=15 style="height:300px"></td>
			</tr>
			</#if>
		</table>
	</div>
</div>

</body>
</html>