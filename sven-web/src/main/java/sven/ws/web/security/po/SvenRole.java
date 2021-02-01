package sven.ws.web.security.po;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/1 14:59
 * @description：
 * @version:
 * @see
 */
@Setter
@Getter
public class SvenRole {

    private List<SvenResource> resourceList;
}
