package com.android.maziotest.mvp;

import com.android.maziotest.R;
import com.android.maziotest.data.model.Pizza;
import com.android.maziotest.rule.TestSchedulersRule;
import com.android.maziotest.testCase.AppRobolectricTestCase;
import com.android.maziotest.ui.ordersummary.OrderSummaryContracts;
import com.android.maziotest.ui.ordersummary.OrderSummaryPresenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.mockito.Mockito.*;

public class OrderSummaryPresenterTest extends AppRobolectricTestCase {

    @Rule
    public TestSchedulersRule rule = new TestSchedulersRule();

    @Inject
    OrderSummaryPresenter presenter;
    @Mock
    OrderSummaryContracts.View view;

    @Before
    public void setUp() throws Exception {
        component().inject(this);
        super.setUp();
        presenter = spy(presenter);
    }

    @Test
    public void onViewAttach_shouldShowOrderSummary() {
        //Given
        Pizza pizza = new Pizza("Pizza", 13.4);
        when(pizzaRepository.getOrderedPizza()).thenReturn(pizza);
        //When
        presenter.onAttachView(view);
        //verify
        verify(view).showOrderSummary(pizza.getName(), String.format(context().getString(R.string.text_pizza_price), pizza.getPrice()));
    }

    @Test
    public void onOpenMenuButton_shouldOpenMenuSceren() {
        //Given
        presenter.onAttachView(view);
        //When
        presenter.onOpenMenuClick();
        //verify
        verify(view).openPizzaMenuScreen();
    }


    @After
    public void unsubAll() {
        presenter.onDestroyView();
    }
}