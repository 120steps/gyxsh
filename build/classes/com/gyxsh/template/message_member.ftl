<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:dt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Author>陈云青</Author>
  <LastAuthor>陈云青</LastAuthor>
  <Created>2016-08-24T03:35:37Z</Created>
  <LastSaved>2016-08-24T03:46:33Z</LastSaved>
  <Version>15.00</Version>
 </DocumentProperties>
 <CustomDocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <KSOProductBuildVer dt:dt="string">2052-8.1.0.2424</KSOProductBuildVer>
 </CustomDocumentProperties>
 <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
  <AllowPNG/>
 </OfficeDocumentSettings>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>12480</WindowHeight>
  <WindowWidth>28800</WindowWidth>
  <WindowTopX>0</WindowTopX>
  <WindowTopY>0</WindowTopY>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
  <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Center"/>
   <Borders/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="12"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
 </Styles>
 <Worksheet ss:Name="Sheet1">
  <Table ss:ExpandedColumnCount="12" ss:ExpandedRowCount="500" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="14.25">
   <Column ss:Width="123"/>
   <Column ss:Width="83.25"/>
   <Column ss:Width="96.75" ss:Span="2"/>
   <Column ss:Index="6" ss:Width="90" ss:Span="3"/>
   <Column ss:Index="10" ss:Width="69.75"/>
   <Column ss:Width="189.75"/>
   <Column ss:Width="196.5"/>
   <Row>
    <Cell><Data ss:Type="String">部门</Data></Cell>
    <Cell><Data ss:Type="String">职位</Data></Cell>
    <Cell><Data ss:Type="String">姓名</Data></Cell>
    <Cell><Data ss:Type="String">性别</Data></Cell>
    <Cell><Data ss:Type="String">班级</Data></Cell>
    <Cell><Data ss:Type="String">手机号码</Data></Cell>
    <Cell ss:StyleID="Default"><Data ss:Type="String">QQ</Data></Cell>
    <Cell><Data ss:Type="String">寝室</Data></Cell>
    <Cell><Data ss:Type="String">学号</Data></Cell>
    <Cell><Data ss:Type="String">政治面貌</Data></Cell>
   </Row>
   <#list members as member>
   <Row>
    <Cell><Data ss:Type="String">${member.merDepartment.deptName}</Data></Cell>
    <Cell><Data ss:Type="String">${member.merStatus.merStatusName}</Data></Cell>
    <Cell><Data ss:Type="String">${member.name}</Data></Cell>
    <Cell><Data ss:Type="String">${member.gender}</Data></Cell>
    <Cell><Data ss:Type="String">${member.clazz}</Data></Cell>
    <Cell><Data ss:Type="String">${member.phone}</Data></Cell>
    <Cell ss:StyleID="Default"><Data ss:Type="String">${member.qq}</Data></Cell>
    <Cell><Data ss:Type="String">${member.dormitory}</Data></Cell>
    <Cell><Data ss:Type="String">${member.studentNum}</Data></Cell>
    <Cell><Data ss:Type="String">${member.polity}</Data></Cell>
   </Row>
   </#list>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.51180555555555551"/>
    <Footer x:Margin="0.51180555555555551"/>
   </PageSetup>
   <Print>
    <ValidPrinterInfo/>
    <PaperSizeIndex>9</PaperSizeIndex>
    <VerticalResolution>0</VerticalResolution>
   </Print>
   <PageBreakZoom>100</PageBreakZoom>
   <Selected/>
   <Panes>
    <Pane>
     <Number>3</Number>
     <ActiveRow>12</ActiveRow>
     <ActiveCol>7</ActiveCol>
    </Pane>
   </Panes>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
 <Worksheet ss:Name="Sheet2">
  <Table ss:ExpandedColumnCount="1" ss:ExpandedRowCount="1" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="14.25">
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.51180555555555551"/>
    <Footer x:Margin="0.51180555555555551"/>
   </PageSetup>
   <Print>
    <ValidPrinterInfo/>
    <PaperSizeIndex>9</PaperSizeIndex>
    <VerticalResolution>0</VerticalResolution>
   </Print>
   <PageBreakZoom>100</PageBreakZoom>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
 <Worksheet ss:Name="Sheet3">
  <Table ss:ExpandedColumnCount="1" ss:ExpandedRowCount="1" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="14.25">
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.51180555555555551"/>
    <Footer x:Margin="0.51180555555555551"/>
   </PageSetup>
   <Print>
    <ValidPrinterInfo/>
    <PaperSizeIndex>9</PaperSizeIndex>
    <VerticalResolution>0</VerticalResolution>
   </Print>
   <PageBreakZoom>100</PageBreakZoom>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
</Workbook>
