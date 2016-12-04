package com.wn.webapp.core.tags.table;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.data.domain.Page;

public class PageTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private static String NEXT_LINE = "\n";
	private Page<Object> page;
	private String formId;
	private StringBuilder html;

	public int doStartTag() throws JspException {
		this.html = new StringBuilder();
		return 0;
	}

	public int doEndTag() throws JspException {
		html.append("<nav>").append(NEXT_LINE);
		html.append("<ul class=\"pagination\">").append(NEXT_LINE);
		html.append(style1());
		html.append("</ul>").append(NEXT_LINE);
		html.append("</nav>").append(NEXT_LINE);
		try {
			pageContext.getOut().write(this.html.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return 6;
	}

	private StringBuilder style1() {
		StringBuilder sb = new StringBuilder();
		sb.append(getFirst());
		sb.append(getPrev());
		if (page.getTotalPages() < 10) {
			if (page.getTotalPages() == 0) {
				sb.append("<li class=\"active\">");
				sb.append("<a href=\"javascript:toPage('").append(this.formId).append("', 1")
						.append(");\" title=\"\">1").append("</a>");
				sb.append("</li>").append(NEXT_LINE);
			}
			for (int i = 1; i <= page.getTotalPages(); i++)
				if (i == page.getNumber() + 1) {
					
					sb.append("<li class=\"active\">");
					sb.append("<a href=\"javascript:toPage('").append(this.formId).append("', ").append(i)
							.append(");\" title=\"\">").append(i).append("</a>");
					sb.append("</li>").append(NEXT_LINE);
				} else {
					
					sb.append("<li>");
					sb.append("<a href=\"javascript:toPage('").append(this.formId).append("', ").append(i)
							.append(");\" title=\"\">").append(i).append("</a>");
					sb.append("</li>").append(NEXT_LINE);
				}
		} else {
			int thisPage = page.getNumber();
			if (thisPage < 5) {
				for (int i = 1; i < 9; i++) {
					if (i == page.getNumber() + 1) {
						sb.append("<li class=\"active\">");
						sb.append("<a href=\"javascript:toPage('").append(this.formId).append("', ").append(i)
								.append(");\" title=\"\">").append(i).append("</a>");
						sb.append("</li>").append(NEXT_LINE);
					} else {
						sb.append("<li>");
						sb.append("<a href=\"javascript:toPage('").append(this.formId).append("', ").append(i)
								.append(");\" title=\"\">").append(i).append("</a>");
						sb.append("</li>").append(NEXT_LINE);
					}
				}
				sb.append("<li><span>...</span></li>").append(NEXT_LINE);
				sb.append("<li>");
				sb.append("<a href=\"javascript:toPage('").append(this.formId).append("', ")
						.append(this.page.getTotalPages()).append(");\" title=\"\">").append(this.page.getTotalPages())
						.append("</a>");
				sb.append("</li>").append(NEXT_LINE);
			} else if (thisPage > page.getTotalPages() - 7) {
				sb.append("<li>");
				sb.append("<a href=\"javascript:toPage('").append(this.formId).append("', ").append(1)
						.append(");\" title=\"\">").append(1).append("</a>");
				sb.append("</li>").append(NEXT_LINE);
				sb.append("<li><span>...</span></li>").append(NEXT_LINE);
				for (int i = page.getTotalPages() - 8; i <= page.getTotalPages(); i++)
					if (i == page.getNumber() + 1) {
						sb.append("<li class=\"active\">");
						sb.append("<a href=\"javascript:toPage('").append(this.formId).append("', ").append(i)
								.append(");\" title=\"\">").append(i).append("</a>");
						sb.append("</li>").append(NEXT_LINE);
					} else {
						sb.append("<li>");
						sb.append("<a href=\"javascript:toPage('").append(this.formId).append("', ").append(i)
								.append(");\" title=\"\">").append(i).append("</a>");
						sb.append("</li>").append(NEXT_LINE);
					}
			} else {
				sb.append("<li>");
				sb.append("<a href=\"javascript:toPage('").append(this.formId).append("', ").append(1)
						.append(");\" title=\"\">").append(1).append("</a>");
				sb.append("</li>").append(NEXT_LINE);
				sb.append("<li><span>...</span></li>").append(NEXT_LINE);
				for (int i = page.getNumber() - 2; i < page.getNumber() + 6; i++) {
					if (i == page.getNumber() + 1) {
						sb.append("<li class=\"active\">");
						sb.append("<a href=\"javascript:toPage('").append(formId).append("', ").append(i)
								.append(");\" title=\"\">").append(i).append("</a>");
						sb.append("</li>").append(NEXT_LINE);
					} else {
						sb.append("<li>");
						sb.append("<a href=\"javascript:toPage('").append(formId).append("', ").append(i)
								.append(");\" title=\"\">").append(i).append("</a>");
						sb.append("</li>").append(NEXT_LINE);
					}
				}
				sb.append("<li><span>...</span></li>").append(NEXT_LINE);
				sb.append("<li>");
				sb.append("<a href=\"javascript:toPage('").append(formId).append("', ").append(page.getTotalPages())
						.append(");\" title=\"\">").append(page.getTotalPages()).append("</a>");
				sb.append("</li>").append(NEXT_LINE);
			}
		}
		sb.append(getNext());
		sb.append(getLast());

		sb.append("<script type=\"text/javascript\">").append(NEXT_LINE);
		sb.append("function toPage(formId, page) {").append(NEXT_LINE);
		sb.append("var $form = $('#' + formId);").append(NEXT_LINE);
		sb.append("reg = /^[0-9]*$/;").append(NEXT_LINE);
		sb.append("if (reg.exec(page + \"\")) {").append(NEXT_LINE);
		sb.append("$(\"#\" + formId + \"_page_id\").val(page);").append(NEXT_LINE);
		sb.append("}").append(NEXT_LINE);
//		sb.append("$form[0].submit();").append(NEXT_LINE);
		sb.append("$('.page-content .submit').trigger(\"click\",[formId]);");
		sb.append("}").append(NEXT_LINE);
		sb.append("</script>").append(NEXT_LINE);
		return sb;
	}

	private String getFirst() {
		String str = "";
		if (!page.isFirst()) {
			str = "<li><a href=\"javascript:toPage('" + formId + "',1)\" title=\"首页\">&laquo;</a></li>";
		} else {
			str = "<li class=\"disabled\"><a href=\"javascript:void(0);\" title=\"首页\">&laquo;</a></li>";
		}
		return str + NEXT_LINE;
	}

	private String getPrev() {
		String str = "";
		if (page.hasPrevious()) {
			str = "<li><a href=\"javascript:toPage('" + formId + "'," + page.getNumber()
					+ ")\" title=\"上页\">&lsaquo;</a></li>";
		} else {
			str = "<li class=\"disabled\"><a href=\"javascript:void(0);\" title=\"上页\">&lsaquo;</a></li>";
		}
		return str + NEXT_LINE;
	}

	private String getNext() {
		String str = "";
		if (page.hasNext()) {
			str = " <li><a href=\"javascript:toPage('" + formId + "'," + (page.getNumber()+2)
					+ ")\" title=\"下页\">&rsaquo;</a></li>";
		} else {
			str = " <li class=\"disabled\"><a href=\"javascript:void(0);\" title=\"下页\">&rsaquo;</a></li>";
		}
		return str + NEXT_LINE;
	}

	private String getLast() {
		String str = "";
		if (!page.isLast()) {
			str = "<li><a href=\"javascript:toPage('" + formId + "'," + page.getTotalPages()
					+ ")\" title=\"末页\">&raquo;</a></li>";
		} else {
			str = "<li class=\"disabled\"><a href=\"javascript:void(0);\" title=\"末页\">&raquo;</a></li>";
		}
		return str + NEXT_LINE;
	}

	public Page<Object> getPage() {
		return page;
	}

	public void setPage(Page<Object> page) {
		this.page = page;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

}
