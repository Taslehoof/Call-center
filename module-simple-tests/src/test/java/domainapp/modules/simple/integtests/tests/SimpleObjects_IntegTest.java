package domainapp.modules.simple.integtests.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.transaction.annotation.Transactional;

import org.apache.causeway.applib.services.iactnlayer.InteractionService;

import domainapp.modules.simple.dom.so.SimpleObjects;
import domainapp.modules.simple.integtests.SimpleModuleIntegTestAbstract;
import jakarta.inject.Inject;

@Transactional
public class SimpleObjects_IntegTest extends SimpleModuleIntegTestAbstract {

    @Inject
    SimpleObjects menu;

    /*@Nested
    public static class listAll extends SimpleObjects_IntegTest {

        @Test
        public void happyCase() {

            // given
            //fixtureScripts.run(new SimpleObject_persona.PersistAll());
            transactionService.flushTransaction();

            // when
            final List<SimpleObject> all = wrap(menu).listAll();

            // then
            //assertThat(all).hasSize(SimpleObject_persona.values().length);
        }

        @Test
        public void whenNone() {

            // when
            final List<SimpleObject> all = wrap(menu).listAll();

            // then
            assertThat(all).hasSize(0);
        }
    }

    @Nested
    public static class create extends SimpleObjects_IntegTest {

        @Test
        public void happyCase() {

            wrap(menu).create("Faz");

            // then
            final List<SimpleObject> all = wrap(menu).listAll();
            assertThat(all).hasSize(1);
        }

        @Test
        public void whenAlreadyExists() {

            // given
            //fixtureScripts.runPersona(SimpleObject_persona.FIZZ);
            interactionService.nextInteraction();

            // we execute this in its own transaction so that it can be discarded
            Try<Void> attempt = transactionService.runTransactional(Propagation.REQUIRES_NEW, () -> {

                // expect
                Throwable cause = assertThrows(Throwable.class, () -> {
                    // when
                    wrap(menu).create("Fizz");
                    transactionService.flushTransaction();
                });

                // also expect
                MatcherAssert.assertThat(cause,
                        ThrowableMatchers.causedBy(DuplicateKeyException.class));
            });


            // then
            assertThat(attempt.isFailure()).isTrue();
            val failureIfAny = attempt.getFailure();
            assertThat(failureIfAny).isPresent();
            assertThat(failureIfAny.get()).isInstanceOf(DuplicateKeyException.class);
            assertThat(failureIfAny.get().getCause()).isInstanceOf(JdbcSQLIntegrityConstraintViolationException.class);

        }

    }*/

    @Inject protected InteractionService interactionService;
}
