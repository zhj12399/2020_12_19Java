package com.ZHJ.dbMenu;
/**
 * @author ZHJ
 * @date 2020/11/29
 */
import com.ZHJ.Things.Menu;

import java.util.List;

public interface MenuRepository {
    boolean AddMenu(Menu menu);
    boolean ModifyMenu(Menu OldMenu,Menu NewMenu);
    boolean DeleteMenu(Menu menu);
    boolean ExistMenu(Menu menu);
    boolean AcceptMenu(Menu menu,String name);
    List<Menu> check();
}
