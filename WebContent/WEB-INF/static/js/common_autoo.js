/**
*通用的系统函数
*
**/
JsCommon = new function () {
    this.CloseDialog=function (oarr) {
        for (var i = 0; i < oarr.length; i++) {
            oarr[i].hide();
        }
    }
    this.getURLParameter = function(name) {
        return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [, ""])[1].replace(/\+/g, '%20')) || null;
    };
    this.isNullOrWhitespace = function(input) {
        if (typeof input === 'undefined' || input == null || input == '') return true;
        return input.toString().replace(/\s/g, '').length < 1;
    };
    this.htmlToText = function(html) {
        var tmp = document.createElement("DIV");
        tmp.innerHTML = html;
        return tmp.textContent || tmp.innerText;
    };
    this.trim = function() {
        return string.replace(/(^\s*)|(\s*$)/g, "");
    };
    this.showConfirm = function(title, pro, callback, data) {
        $.messager.confirm(title, pro, function(r) {
            if (r) {
                callback(data);
            }
        });
    };
    this.jqueryAjaxError = function(jqXHR, textStatus, errorThrown) {       
        JsCommon.waitingcancel();
        $.messager.alert('提示', errorThrown, "warning");
    };
	this.cutstr = function cutstr(str, len) {
		var str_length = 0;
		var str_len = 0;
		str_cut = new String();
		str_len = str.length;
		for (var i = 0; i < str_len; i++) {
			a = str.charAt(i);
			str_length++;
			if (escape(a).length > 4) {
				//中文字符的长度经编码之后大于4
				str_length++;
			}
			str_cut = str_cut.concat(a);
			if (str_length >= len) {
				str_cut = str_cut.concat("...");
				return str_cut;
			}
		}
		//如果给定字符串小于指定长度，则返回源字符串；
		if (str_length < len) {
			return str;
		}
	};
	this.createGUID = function() {

        function s4() {
            return Math.floor((1 + Math.random()) * 0x10000)
                .toString(16)
                .substring(1);
        }

        return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
            s4() + '-' + s4() + s4() + s4();
    };
    this.isNumeric=function(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }
    this.IsReload = false;
};
/*easyui datagrid function*/
JsCommon.getEasyuiGridSelectedRowFieldData = function(obj, field) {
    var row = obj.datagrid('getSelected');
    var val = '';
    if (row) {
        val = row[field];
    }
    if (this.isNullOrWhitespace(val)) {
        return '';
    } else {
        return val;
    }
};
JsCommon.getEasyuiGridSelectedRowByIndex = function(obj, index) {
    obj.datagrid('selectRow', index);
    var row = obj.datagrid('getSelected');
    if (row) {
        return row;
    } else {
        return null;
    }
};
JsCommon.reloadEasyuiDataGrid = function(obj, param) {
    obj.datagrid('reload', param).datagrid('getPager').pagination({ pageNumber: 1 });
};
JsCommon.reloadEasyuiTreeGrid = function(obj, param) {
    obj.treegrid('reload', param);
};
JsCommon.reHeightEasyuiDataGrid = function(obj,minheight) {
    var view = obj.closest('.datagrid-view');
    var body = obj.closest('.datagrid-view').find('.datagrid-body');
    var panelbody = view.closest('.panel-body');
    
    var btable = body.find('.datagrid-btable');
    var height = btable.height();
    if (height < minheight) {
        return;
    }
    body.height(height);
    view.height(height + 30);
    var pager = obj.datagrid('getPager');
    if (pager.length>0) {
        panelbody.height(height + 60);
    } else {
        panelbody.height(height + 20);
    }
}
JsCommon.showEasyuiGridSelectedRowInformation = function(obj, fields,isdownloaddoc) {
    var row = obj.datagrid('getSelected');
    var val = '';
    if (row) {
        var div = $('<div></div>');
        var table = $('<table class="tableedit"></table>').appendTo(div);
        for (var i = 0; i < fields.length; i++) {
            var fieldobj = fields[i];
            var title = fieldobj.title;
            var field = fieldobj.field;
            var data = row[field];
            if (fieldobj.ismaintext == true) {
                //data = JsCommon.formatMainText(fieldobj.Pid, fieldobj.NameView, field);
                JsCommon.formatDownLoadMainText(table, row, isdownloaddoc);
                continue;
            }
            if (fieldobj.isattach == true) {
                data = JsCommon.formatAttach(data);
            }
            if (fieldobj.isfiles == true) {
                if (JsCommon.isNullOrWhitespace(data)) {
                    continue;
                }
	            var datastr = '';
	            $.each(data,function(v) {
	            	var temp = data[v].Details;
		            var str = '';
		            $.each(temp,function(n) {
			            str = str + temp[n] + '<br/>';
		            });
		            if (JsCommon.isNullOrWhitespace(str)) {
			            str = '无';
		            }
		            datastr = datastr + '<a class="showdetail" style="color:blue;text-decoration:underline;" tag="' + str + '">《' + data[v].NameView + '》</a><br>';
	            });
	            data = datastr;
            }
            if (JsCommon.isNullOrWhitespace(data)) {                data = "";            }
            $('<tr><td class="tableditfirsttd">' + title + ':</td><td>' + data + '</td></tr>').appendTo(table);
        }
        div.dialog({
            title: '详细信息',
            closed: true,
            modal: true,
            width: 500,
            height: 400,
            iconCls: 'icon-information',
            resizable: true,
            left: ($(window).width() - 500) * 0.5,
            top: ($(window).height() - 400) * 0.5,
            onClose: function() {
                $(this).dialog('destroy');
            },
            onOpen: function () {
                $('.showdetail').each(function () {
                    var tag = $(this).attr('tag');
                    $(this).tooltip({ position: 'top', content: tag });
                });
            }
        }).dialog("open");
    }
};
JsCommon.showEasyuiGridSelectedRowInformation2 = function (obj, fields, div, isdownloaddoc) {
    var row = obj.datagrid('getSelected');
    div.empty();
    var val = '';
    if (row) {        
        var table = $('<table class="tableedit"></table>').appendTo(div);
        for (var i = 0; i < fields.length; i++) {
        	var fieldobj = fields[i];
            var title = fieldobj.title;
            var field = fieldobj.field;
            var data = row[field];
            if (JsCommon.isNullOrWhitespace(data)) {
                data = "";
            }
            if (fieldobj.ismaintext == true) {
                //data = JsCommon.formatMainText(fieldobj.Pid, fieldobj.NameView,field);
                JsCommon.formatDownLoadMainText(table, row, isdownloaddoc);
                continue;
            }
            if (fieldobj.isattach == true) {
            	data = JsCommon.formatAttach(data);
            }
	        if (fieldobj.isfiles == true) {
		        var datastr = '';
		        if (!JsCommon.isNullOrWhitespace(data)) {
			        $.each(data, function(v) {
				        var temp = data[v].Details;
				        var str = '';
				        if (JsCommon.isNullOrWhitespace(temp)) {
					        return;
				        }
				        $.each(temp, function(n) {
					        str = str + temp[n] + '<br/>';
				        });
				        if (JsCommon.isNullOrWhitespace(str)) {
					        str = '无';
				        }
				        datastr = datastr + '<a class="showdetail" style="color:blue;text-decoration:underline;" tag="' + str + '">《' + data[v].NameView + '》</a><br>';
			        });

			        data = datastr;
		        }
	        }
	        if (JsCommon.isNullOrWhitespace(data)) { data = ""; }
	        $('<tr><td class="tableditfirsttd">' + title + ':</td><td>' + data + '</td></tr>').appendTo(table);
		}
		$('.showdetail').each(function () {
			var tag = $(this).attr('tag');
			$(this).tooltip({ position: 'top', content: tag });
		});
    }
};

/*easyui waiting*/
JsCommon.waiting = function (title, msg) {
    $.messager.progress({
        title: title,
        msg: msg
    });
    //setTimeout(function () {
    //    try {
    //        JsCommon.waitingcancel();//5秒以后自动结束
    //    } catch (e) {

    //    }
    //}, 5000);
   
};
JsCommon.waitingcancel = function () {
    $.messager.progress('close');
};
/*dynamic add uploadify*/
JsCommon.addUploadify = function (container,files,typeName,buttonText,multi,fileExt,fileDesc,idnumber) {
    container.empty();
    if (JsCommon.isNullOrWhitespace(idnumber)) {
        idnumber = "";
    }
    var input = $('<input id="file_upload' + idnumber + '" name="file_upload' + idnumber + '" type="file"><input id="FileIds' + idnumber + '" name="FileIds" type="hidden">').appendTo(container);
    $('#file_upload' + idnumber).uploadify({
        'cancelImg': UrlContent("~/contents/uploadify/cancel.png"),
        'uploader': UrlContent('~/contents/uploadify/uploadify.swf'),
        'script': UrlContent("~/Uploadify/Import/"),
        'buttonImg': UrlContent("~/contents/uploadify/selectfile.png"),
        'fileExt': fileExt,
        'fileDesc': fileDesc,       
        'auto': true,
        'multi': multi,
        'scriptData': { 'typeName': typeName },
        removeCompleted: false,
        'onComplete': function (event, ID, fileObj, response, data) {
            var result = eval("[" + response + "]")[0];
            var fileid = result.FileId;
            var filename = result.FileName;
            var fileids = $('#FileIds'+idnumber).val();
            if (!JsCommon.isNullOrWhitespace(fileids)) {
                fileids += ",";
            }
            $('#FileIds' + idnumber).val(fileids + fileid);
            $('<input type="hidden" id="' + 'file_upload' + idnumber + 'value' + "file_" + ID + '" value="' + fileid + '"/>').appendTo($('#file_upload' + idnumber + 'Queue'));
            $('.showhideclass').show();           
        },
        'onCancel': function (event, ID, fileObj, data) {
            var fileid = $('#' + 'file_upload' + idnumber + 'value' + "file_" + ID).val();
            var fileids = $('#FileIds'+idnumber).val();
            fileids = fileids.replace(fileid, "");
            $('#FileIds'+idnumber).val(fileids);
            $('.showhideclass').show();        
        },
        'onSelect': function (file) {
            $('.showhideclass').hide();
        }
    });
    if (!JsCommon.isNullOrWhitespace(files)) {
        for (var i = 0; i < files.length; i++) {
            var val = files[i];
            var key = val.id;
            var name = val.text;
            var queueid = "file_upload"+idnumber+"Queue";
            $('<div id=file_upload'+idnumber + key + ' class="uploadifyQueueItem">' +
                ' <div class="cancel">' +
                    ' <a href="javascript:$(\'#file_upload' + idnumber + '\').uploadifyCancel(\'' + key + '\')">' +
                    '    <img src="/Contents/uploadify/cancel.png" border="0">' +
                    '</a>' +
                '</div>' +
                '<span class="fileName">' + name + ' </span><span class="percentage">' +
                '</span>' +
                '<input type="hidden" id="' + 'file_upload' + idnumber + 'value' + "file_" + key + '" value="' + key + '"/>' +
            '</div>').appendTo($('#' + queueid));
            var fileids = $('#FileIds'+idnumber).val();
            $('#FileIds'+idnumber).val(fileids + "," + key);
        }
    }    
}

JsCommon.formatAttach = function (val, showtext,filename) {
    var attach = "";
    if (JsCommon.isNullOrWhitespace(filename)) {
        filename = "";
    }
    if (!JsCommon.isNullOrWhitespace(val)) {
        var values = val;
        for (var i = 0; i < values.length; i++) {
            var v = values[i];
            var key = v.id;
            var name = v.text;
            if (!JsCommon.isNullOrWhitespace(showtext)) {
                name = showtext;
            } 
            attach += '<a href=' + UrlContent("~/Uploadify/Import/downloadAttach?filename="+filename+"&attachId=" + key) + ' >' + name + '</a><br>';
        }
    }
    return attach;
}

/*只允许船长下载PDF格式的体系文件 体系管理员可以下DOC格式*/
JsCommon.formatDownLoadMainText = function (table,row,isdownloaddoc) {
    
    //SystemFile = 0,   
    //RecordTable = 1,   
    //StructureRecordTable = 2,   
    //StructureSystemFile = 3,  
    //CatalogFile = 4,   
    //IndexFile = 5
    
    var namecn = row.FileNoLocal  + row.NameLocal + ':' + row.Version;
    var nameen = row.FileNo + row.Name + ':' + row.Version;
    if (JsCommon.isNullOrWhitespace(row.FileNoLocal) && JsCommon.isNullOrWhitespace(row.NameLocal)) {
        namecn = '';
    }
    if (JsCommon.isNullOrWhitespace(row.FileNo) && JsCommon.isNullOrWhitespace(row.Name)) {
        nameen = '';
    }
    if (row.QualityFileType==0) { //体系文件
        if (row.Attach.length > 0) {
            if (row.fileType.indexOf("DOC") >= 0) {
                if (isdownloaddoc) {                     
                    var data = '<a href=' + UrlContent("~/Uploadify/Import/DownloadDocumentWord?lang=en_US&QualityFileInfoId=" + row.Id) + ' >' + nameen + '</a>';
                    $('<tr><td class="tableditfirsttd">DownLoad Doc(EN):</td><td>' + data + '</td></tr>').appendTo(table);
                }
                //PDF格式始终可以下，只有授权才能下doc格式的                
                var data = '<a href=' + UrlContent("~/Uploadify/Import/DownloadDocumentPDF?lang=en_US&QualityFileInfoId=" + row.Id) + ' >' + nameen + '</a>';
                $('<tr><td class="tableditfirsttd">DownLoad Pdf(EN):</td><td>' + data + '</td></tr>').appendTo(table);
            } else {
                var data = JsCommon.formatAttach(row.Attach, nameen,nameen);
                $('<tr><td class="tableditfirsttd">DownLoad (EN):</td><td>' + data + '</td></tr>').appendTo(table);
            }            
        }
        if (row.AttachLocal.length > 0) {
            if (row.fileTypeLocal.indexOf("DOC") >= 0) {
                if (isdownloaddoc) {                    
                    var data = '<a href=' + UrlContent("~/Uploadify/Import/DownloadDocumentWord?lang=zh_CN&QualityFileInfoId=" + row.Id) + ' >' + namecn + '</a>';
                    $('<tr><td class="tableditfirsttd">下载 Doc(中文):</td><td>' + data + '</td></tr>').appendTo(table);
                }
                //PDF格式始终可以下，只有授权才能下doc格式的             
                var data = '<a href=' + UrlContent("~/Uploadify/Import/DownloadDocumentPDF?lang=zh_CN&QualityFileInfoId=" + row.Id) + ' >' + namecn + '</a>';
                $('<tr><td class="tableditfirsttd">下载 Pdf(中文):</td><td>' + data + '</td></tr>').appendTo(table);
            } else {
                var data = JsCommon.formatAttach(row.AttachLocal, namecn,namecn);
                $('<tr><td class="tableditfirsttd">下载 (中文):</td><td>' + data + '</td></tr>').appendTo(table);
            }
        }
    } else if (row.QualityFileType == 3 || row.QualityFileType == 4 || row.QualityFileType == 5) {//结构化体系文件
        if (!JsCommon.isNullOrWhitespace(row.Name)) {
            if (isdownloaddoc) {              
                var data = '<a href=' + UrlContent("~/Uploadify/Import/DownloadDocumentWord?lang=en_US&QualityFileInfoId=" + row.Id) + ' >' + nameen + '</a>';
                $('<tr><td class="tableditfirsttd">DownLoad Doc(EN):</td><td>' + data + '</td></tr>').appendTo(table);
            }
            //PDF格式始终可以下，只有授权才能下doc格式的         
            var data = '<a href=' + UrlContent("~/Uploadify/Import/DownloadDocumentPDF?lang=en_US&QualityFileInfoId=" + row.Id) + ' >' + nameen + '</a>';
            $('<tr><td class="tableditfirsttd">DownLoad Pdf(EN):</td><td>' + data + '</td></tr>').appendTo(table);
        }
        if (!JsCommon.isNullOrWhitespace(row.NameLocal)) {
            if (isdownloaddoc) {               
                var data = '<a href=' + UrlContent("~/Uploadify/Import/DownloadDocumentWord?lang=zh_CN&QualityFileInfoId=" + row.Id) + ' >' + namecn + '</a>';
                $('<tr><td class="tableditfirsttd">下载 Doc(中文):</td><td>' + data + '</td></tr>').appendTo(table);
            }
            //PDF格式始终可以下，只有授权才能下doc格式的          
            var data = '<a href=' + UrlContent("~/Uploadify/Import/DownloadDocumentPDF?lang=zh_CN&QualityFileInfoId=" + row.Id) + ' >' + namecn + '</a>';
            $('<tr><td class="tableditfirsttd">下载 Pdf(中文):</td><td>' + data + '</td></tr>').appendTo(table);
        }       
    } else if (row.QualityFileType == 1 || row.QualityFileType == 2) {
        if (row.Attach.length > 0) {
            var data = JsCommon.formatAttach(row.Attach, nameen,nameen);
            $('<tr><td class="tableditfirsttd">DownLoad (EN):</td><td>' + data + '</td></tr>').appendTo(table);
        }
        if (row.AttachLocal.length > 0) {
            var data = JsCommon.formatAttach(row.AttachLocal, namecn, nameen);
            $('<tr><td class="tableditfirsttd">下载 (中文):</td><td>' + data + '</td></tr>').appendTo(table);
        }
    }  
}

JsCommon.DetailPushData = function (data) {
    data.push({ title: '中文名称', field: 'NameLocal' });
    data.push({ title: '英文名称', field: 'Name' });
    data.push({ title: '中文编号', field: 'FileNoLocal' });
    data.push({ title: '英文编号', field: 'FileNo' });
    data.push({ title: '版本号', field: 'Version' });
    data.push({ title: '提交人', field: 'Sponsor' });
    data.push({ title: '发布日期', field: 'SponsorDate' });
    data.push({ title: '生效日期', field: 'EnableDate' });
    data.push({ title: '编制部门', field: 'MajorDepartmentView' });
    data.push({ title: '适用岗位', field: 'RelationDepartmentView' });
    data.push({ title: '适用船舶', field: 'ShipIdView' });
    data.push({ title: '文件类型', field: 'QualityFileTypeView' });
    data.push({ title: '关联标准', field: 'Standards', isfiles: true });
    data.push({ title: '关联文件', field: 'Files', isfiles: true });   
}

JsCommon.DetailPushData2 = function (data) {
    data.push({ title: '中文名称', field: 'NameLocal' });
    data.push({ title: '英文名称', field: 'Name' });
    data.push({ title: '中文编号', field: 'FileNoLocal' });
    data.push({ title: '英文编号', field: 'FileNo' });
    data.push({ title: '版本号', field: 'Version' });
    data.push({ title: '提交人', field: 'Sponsor' });
    data.push({ title: '发布日期', field: 'SponsorDate' });
    data.push({ title: '生效日期', field: 'EnableDate' });
    data.push({ title: '编制部门', field: 'MajorDepartmentView' });
    data.push({ title: '适用岗位', field: 'RelationDepartmentView' });
    data.push({ title: '适用船舶', field: 'ShipIdView' });
    data.push({ title: '工作提醒周期', field: 'DicEventInfoView' });
    data.push({ title: '文件类型', field: 'QualityFileTypeView' });
    data.push({ title: '所属标准', field: 'Standards', isfiles: true });
}