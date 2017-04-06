package by.bsuir.tag;

import by.bsuir.command.constant.Attribute;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@Log4j
public class PaginationTag extends TagSupport {
    private static long serialVersionUID = 1L;
    private static final int OFFSET = 4;
    private static final int MAX_VISIBLE_PAGE = 9;

    private int totalPages;
    private int currentPage;
    private String command;

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setCommand(String command){
        this.command = command;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String contextPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
            String category = pageContext.getRequest().getParameter(Attribute.CATEGORY_ID);
            if(this.totalPages <= 1){
                return SKIP_BODY;
            }
            StringBuilder sb = new StringBuilder("<nav class='right'><ul class='pagination'>");
            sb.append(this.getFirst(contextPath, category)).append(this.getPrev(contextPath, category));
            int start = this.currentPage - OFFSET < 1 ? 1 : this.currentPage - OFFSET;
            int end = this.currentPage + OFFSET > this.totalPages ? this.totalPages : this.currentPage + OFFSET;
            for(int i = start; i <= end; i++){
                sb.append("<li");
                if(i == this.currentPage){
                    sb.append(" class='disabled'").append("><a href='javascript:void(0);");
                } else {
                    sb.append("><a href='");
                    sb.append(contextPath).append("/Controller?").append(this.command).append("&page=").append(i);
                    if(category != null){
                        sb.append("&category=").append(category);
                    }
                }
                sb.append("'>").append(i).append("</a></li>");
            }
            sb.append(this.getNext(contextPath, category)).append(this.getLast(contextPath, category));
            sb.append("</ul></nav>");
            pageContext.getOut().write(sb.toString());
        } catch(IOException e){
            log.error(e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    private String getFirst(String contextPath, String category){
        if(this.totalPages <= MAX_VISIBLE_PAGE){
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            String disabled;
            String href;
            if(this.currentPage == 1){
                disabled = " class='disabled'";
                href = "javascript:void(0)";
            } else {
                disabled = "";
                StringBuilder hrefSb = new StringBuilder(contextPath);
                hrefSb.append("/Controller?").append(this.command).append("&page=1");
                if(category != null){
                    hrefSb.append("&category=").append(category);
                }
                href = hrefSb.toString();
            }

            sb.append("<li").append(disabled).append("><a href='").append(href).append("'");
            sb.append("<span aria-hidden='true'>←</span></a></li>");
            return sb.toString();
        }
    }

    private String getPrev(String contextPath, String category){
        StringBuilder sb = new StringBuilder();
        String disabled;
        String href;
        if(this.currentPage == 1){
            disabled = " class='disabled'";
            href = "javascript:void(0)";
        } else {
            disabled = "";
            StringBuilder hrefSb = new StringBuilder(contextPath);
            hrefSb.append("/Controller?").append(this.command).append("&page=").append(this.currentPage - 1);
            if(category != null){
                hrefSb.append("&category=").append(category);
            }
            href = hrefSb.toString();
        }

        sb.append("<li").append(disabled).append("><a href='").append(href).append("' aria-label='Previous'>");
        sb.append("<span aria-hidden='true'>&laquo;</span></a></li>");
        return sb.toString();
    }

    private String getNext(String contextPath, String category){
        StringBuilder sb = new StringBuilder();
        String disabled;
        String href;
        if(this.currentPage == this.totalPages){
            disabled = " class='disabled'";
            href = "javascript:void(0)";
        } else {
            disabled = "";
            StringBuilder hrefSb = new StringBuilder(contextPath);
            hrefSb.append("/Controller?").append(this.command).append("&page=").append(this.currentPage + 1);
            if(category != null){
                hrefSb.append("&category=").append(category);
            }
            href = hrefSb.toString();
        }

        sb.append("<li").append(disabled).append("><a href='").append(href).append("' aria-label='Previous'>");
        sb.append("<span aria-hidden='true'>&raquo;</span></a></li>");
        return sb.toString();
    }

    private String getLast(String contextPath, String category){
        if(this.totalPages <= MAX_VISIBLE_PAGE){
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            String disabled;
            String href;
            if(this.currentPage == this.totalPages){
                disabled = " class='disabled'";
                href = "javascript:void(0)";
            } else {
                disabled = "";
                StringBuilder hrefSb = new StringBuilder(contextPath);
                hrefSb.append("/Controller?").append(this.command).append("&page=").append(this.totalPages);
                if(category != null){
                    hrefSb.append("&category=").append(category);
                }
                href = hrefSb.toString();
            }

            sb.append("<li").append(disabled).append("><a href='").append(href).append("'>");
            sb.append("<span aria-hidden='true'>→</span></a></li>");
            return sb.toString();
        }
    }
}
