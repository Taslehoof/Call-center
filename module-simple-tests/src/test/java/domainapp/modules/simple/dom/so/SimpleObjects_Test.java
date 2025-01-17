package domainapp.modules.simple.dom.so;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.persistence.jpa.applib.services.JpaSupportService;

@ExtendWith(MockitoExtension.class)
class SimpleObjects_Test {

    @Mock RepositoryService mockRepositoryService;
    @Mock JpaSupportService mockJpaSupportService;
    @Mock
    SimpleObjectRepository mockSimpleObjectRepository;

    SimpleObjects objects;

    @BeforeEach
    public void setUp() {
        objects = new SimpleObjects(mockRepositoryService, mockJpaSupportService, mockSimpleObjectRepository);
    }

    /*@Nested
    class create {

        @Test
        void happyCase() {

            // given
            final String someName = "Foobar";

            // expect
            when(mockRepositoryService.persist(
                    argThat((ArgumentMatcher<SimpleObject>) simpleObject -> Objects.equals(simpleObject.getName(), someName)))
            ).then((Answer<SimpleObject>) invocation -> invocation.getArgument(0));

            // when
            final SimpleObject obj = objects.create(someName);

            // then
            assertThat(obj).isNotNull();
            assertThat(obj.getName()).isEqualTo(someName);
        }
    }

    @Nested
    class ListAll {

        @Test
        void happyCase() {

            // given
            final List<SimpleObject> all = new ArrayList<>();

            // expecting
            when(mockSimpleObjectRepository.findAll())
                .thenReturn(all);

            // when
            final List<SimpleObject> list = objects.listAll();

            // then
            assertThat(list).isEqualTo(all);
        }
    }*/
}
