package com.atherys.core.template;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BookTemplate extends AbstractTemplate<BookView> {

    // The title of all book templates is the same because BookViews never display them to the player
    public static final Text BOOK_TEMPLATE_TITLE = Text.of("Book Title");

    // BookView authors are all the same for the same reason
    public static final Text BOOK_TEMPLATE_AUTHOR = Text.of("Author");

    protected List<Text> pages = new ArrayList<>(50);

    protected int lastPageAdded = 0;

    protected void setPage(int index, Text page) {
        if ( index < 0 || index >= pages.size() ) {
            return;
        }

        pages.set(index, page);
    }

    protected void removePage(int index) {
        if ( index < 0 || index >= pages.size() ) {
            return;
        }

        pages.remove(index);
    }

    protected void addPage(Text page) {
        setPage(lastPageAdded++, page);
    }

    protected void removeLastPage() {
        removePage(lastPageAdded--);
    }

    @Override
    public BookView render() {
        BookView.Builder viewBuilder = BookView.builder()
                .title(BOOK_TEMPLATE_TITLE)
                .author(BOOK_TEMPLATE_AUTHOR);

        pages.forEach(page -> viewBuilder.addPage(applyAttributesTo(page)));

        return viewBuilder.build();
    }

    @Override
    public void showTo(Player player) {
        player.sendBookView(render());
    }
}
