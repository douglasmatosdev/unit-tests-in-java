package com.douglasmatosdev;

import com.douglasmatosdev.entities.User;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

    @Test
    public void test() {
        Assert.assertTrue(true);
        Assert.assertFalse(false);

        Assert.assertEquals(1, 1);

        // Para Double/float deve-se adicionar um terceiro parâmetro que é a margem de erro
        Assert.assertEquals(0.51, 0.51, 0.01);
        Assert.assertEquals(0.51234, 0.512, 0.001);
        Assert.assertEquals(Math.PI, 3.14, 0.01);

        // comparação de primitivos e classes
        int i = 5;
        Integer i2 = 5;
        // Vai dar erro
        // Assert.assertEquals(i, i2);

        // Modo certo
        Assert.assertEquals(Integer.valueOf(i), i2);
        // outra forma
        Assert.assertEquals(i, i2.intValue());

        // String
        Assert.assertEquals("bola", "bola");
        // Mensagem descritiva
        Assert.assertEquals("Apenas uma mensagem descritiva",  "bola", "bola");
        Assert.assertNotEquals("bola", "casa");
        Assert.assertTrue("bola".equalsIgnoreCase("BOLA"));
        Assert.assertTrue("bola".startsWith("bo"));

        // Objetos com instâncias diferentes mas é o mesmo uruário
        User u1 = new User("Usuário 1");
        User u2 = new User("Usuário 1");
        User u3 = u2;
        Assert.assertEquals(u1, u2);

        // Objetos com instâncias iguais mas não é o mesmo uruário
        Assert.assertSame(u2, u3);
        Assert.assertSame(u2, u2);
        Assert.assertNotSame(u1, u2);

        //Verificando se o Objeto é null
        u3 = null;
        Assert.assertTrue(u3 == null);
        Assert.assertNull(u3);
        Assert.assertNotNull(u1);
    }
}
