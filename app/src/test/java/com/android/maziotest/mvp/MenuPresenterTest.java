package com.android.maziotest.mvp;

import com.android.maziotest.rule.TestSchedulersRule;
import com.android.maziotest.testCase.AppRobolectricTestCase;
import com.android.maziotest.testUtils.TestDataGenerator;
import com.android.maziotest.ui.menu.MenuContracts;
import com.android.maziotest.ui.menu.MenuPresenter;
import io.reactivex.Observable;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

public class MenuPresenterTest extends AppRobolectricTestCase {

    @Rule
    public TestSchedulersRule rule = new TestSchedulersRule();

    @Inject
    MenuPresenter presenter;
    @Mock
    MenuContracts.View view;

    @Before
    public void setUp() throws Exception {
        component().inject(this);
        super.setUp();
        presenter = spy(presenter);
    }

    @Test
    public void onViewAttach_shouldShowPizzas() {
        //Given
        when(apiHelper.getPizzas()).thenReturn(Observable.just(TestDataGenerator.getPizzas()));
        //When
        presenter.onAttachView(view);
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        //verify
        verify(view).showPizzas(TestDataGenerator.getPizzas());
    }

    @Test
    public void onCustomPizzaClick_shouldOpenCustomPizzaScreen() {
        //Given
        presenter.onAttachView(view);
        //When
        presenter.onSelectCustomizedPizzaClick();
        //verify
        verify(view).openCustomPizzaSelectionScreen();
    }

    @Test
    public void onSelectPizzaClick_shouldOpenOrderSummary() {
        //Given
        presenter.onAttachView(view);
        //When
        presenter.onSelectPizzaClick(anyInt());
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        //verify
        verify(view).openConfirmationScreen();
    }


    @After
    public void unsubAll() {
        presenter.onDestroyView();
    }
}