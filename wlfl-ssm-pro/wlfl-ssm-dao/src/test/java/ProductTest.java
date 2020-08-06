import com.ginger.wlfl.dao.IProductDao;
import com.ginger.wlfl.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(locations = "classpath:springContext-dao.xml")
public class ProductTest {

    @Autowired
    IProductDao productDao;

    @Test
    public void testFindAll() {
        List<Product> productList = productDao.findAll();
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}
