package com.android.maziotest.mvp;

import com.android.maziotest.rule.TestSchedulersRule;
import com.android.maziotest.testCase.AppRobolectricTestCase;
import com.android.maziotest.testUtils.TestDataGenerator;
import com.android.maziotest.ui.custompizza.CustomPizzaContracts;
import com.android.maziotest.ui.custompizza.CustomPizzaPresenter;
import io.reactivex.Observable;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;


public class CustomPizzaPresenterTest extends AppRobolectricTestCase {

    @Rule
    public TestSchedulersRule rule = new TestSchedulersRule();

    @Inject
    CustomPizzaPresenter presenter;
    @Mock
    CustomPizzaContracts.View view;

    @Before
    public void setUp() throws Exception {
        component().inject(this);
        super.setUp();
        presenter = spy(presenter);
    }

    @Test
    public void onViewAttach_shouldShowPizzaFlavors() {
        //Given
        doReturn(Observable.just(TestDataGenerator.getPizzas())).when(apiHelper).getPizzas();
        //When
        presenter.onAttachView(view);
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        //verify
        verify(view).showPizzaFlavors(any());
    }

    @Test
    public void onSelectPizzaClick_shouldRefreshData() {
        //Given
        presenter.onAttachView(view);
        //When
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        presenter.onSelectPizza(1);
        //verify
        verify(view).showPizzaSelection(anyString(), anyString());
        verify(view, times(2)).showPizzaFlavors(any());
    }

    @Test
    public void onSelectPizzaClick_shouldShowErrorWhenTwoFlavorsSelected() {
        //Given
        presenter.onAttachView(view);
        //When
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        presenter.onSelectPizza(0);
        presenter.onSelectPizza(1);
        presenter.onSelectPizza(2);
        //verify
        verify(view).showAlreadySelectedTwoPizzasMessage();
    }


    @Test
    public void onCancelPizzaClick_shouldRefreshData() {
        //Given
        presenter.onAttachView(view);
        //When
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        presenter.onSelectPizza(1);
        //verify
        verify(view).showPizzaSelection(anyString(), anyString());
        verify(view, times(2)).showPizzaFlavors(any());
    }

    @Test
    public void onConfirmPizzaSelectionClick_shouldOpenOrderSummary() {
        //Given
        presenter.onAttachView(view);
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        presenter.onSelectPizza(0);
        presenter.onSelectPizza(1);
        //When
        presenter.onConfirmSelection();
        //verify
        verify(view).openConfirmationScreen();
    }


    @After
    public void unsubAll() {
        presenter.onDestroyView();
    }
}