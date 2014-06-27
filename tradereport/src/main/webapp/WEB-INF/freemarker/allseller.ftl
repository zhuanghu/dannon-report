<table class="bass_table" border=1 cellspacing=0 cellpadding=0>
	<tr style=" background:#000; color:#fff;">
		<td width=200>Seller</td>
		<td width=100>Date</td>
		<td width=150>Item</td>
		<td width=200>Title</td>
		<td width=80>Order Number</td>
		<td width=80>Sales Number</td>
		<td width=80>Refund Number</td>
	</tr>
	<#if sellerstatistics?exists>
	<#list sellerstatistics as ss>
	<tr bgcolor="#ffffff">
		<td>${ss.sellerNick}</td>
		<td>${ss.recordTime?string("yyyy-MM-dd")}</td>
		<td>${ss.itemId?c}</td>
		<td>${ss.title}</td>
		<td>${ss.orderNumber!}</td>
		<td>${ss.salesNumber!}</td>
		<td>${ss.refundNumber!}</td>
	</tr>
	</#list>
	<#else>
	<tr>
		<td colspan=7 style="height:300px"></td>
	</tr>
	</#if>
</table>
