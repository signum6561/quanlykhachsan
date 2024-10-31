package com.nhom3_221404.usecase.Delete;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.nhom3_221404.dto.DeleteInvoiceInputDTO;
import com.nhom3_221404.ui.presenter.DeleteInvoicePresenter;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceUseCase;

public class DeleteInvoiceUseCasetest {
    @Test
    public void testDeleteInvoiceSuccess() {
        TestDeleteInvoiceDatabase database = new TestDeleteInvoiceDatabase(true);
        DeleteInvoicePresenter presenter = new DeleteInvoicePresenter();
        DeleteInvoiceUseCase useCase = new DeleteInvoiceUseCase(database, presenter); 
        DeleteInvoiceInputDTO input = new DeleteInvoiceInputDTO();
        input.setInvoiceId("001");
        useCase.execute(input);
        assertTrue(presenter.getViewModel().isSuccess());
        assertEquals("invoice delete success", presenter.getViewModel().getMessage());
    }
    @Test
    public void testDeleteInvoiceFailure() {
        TestDeleteInvoiceDatabase database = new TestDeleteInvoiceDatabase(false);
        DeleteInvoicePresenter presenter = new DeleteInvoicePresenter();
        DeleteInvoiceUseCase useCase = new DeleteInvoiceUseCase(database, presenter);
        DeleteInvoiceInputDTO input = new DeleteInvoiceInputDTO();
        input.setInvoiceId("002");
        useCase.execute(input);
        assertFalse(presenter.getViewModel().isSuccess());
        assertEquals("failure delete invoice", presenter.getViewModel().getMessage());
    }
    private static class TestDeleteInvoiceDatabase implements DeleteInvoiceDatabaseBoundary {
        private final boolean shouldSuccess;
        public TestDeleteInvoiceDatabase(boolean shouldSuccess) {
            this.shouldSuccess = shouldSuccess;
        }
        @Override
        public boolean deleteInvoice(String invoiceId) {
            return shouldSuccess;
        }
    }
}