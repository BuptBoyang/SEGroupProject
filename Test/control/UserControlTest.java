package control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entity.User;

class UserControlTest {

	@Test
	void formatTest() {

		String email[] = new String[5];
		String id[] = new String[5];
		String name[] = new String[5];
		boolean er[] = new boolean[5];
		boolean ir[] = new boolean[5];
		boolean dup[] = new boolean[5];

		email[0] = new String("6325456@qq.com");
		email[1] = new String("161188101@qmul.ac.uk");
		email[2] = new String("zd@aa.");
		email[3] = new String("liuxian@asd.asd");
		email[4] = new String("qianxin@qq");
		
		id[0] = new String("161188100");
		id[1] = new String("161188101");
		id[2] = new String("161188102");
		id[3] = new String("161188101");
		id[4] = new String("16118810a");
		
		name[0] = new String("liming");
		name[1] = new String("wangling");
		name[2] = new String("zhaodong");
		name[3] = new String("liuxian");
		name[4] = new String("qianxin");
		
		for (int i = 0; i < 5; i++) {
			User user = new User(id[i], name[i] ,email[i]);
			er[i] = UserControl.isEmailLegal(email[i]);
			ir[i] = UserControl.isIDLegal(id[i]);
			dup[i] = UserControl.isDuplication(id[i]);
			if(er[i]==true && ir[i]==true && dup[i]==false)
				UserControl.userArrayList.add(user);
		}
		
		assertEquals(2,UserControl.userArrayList.size());
		assertEquals(true,er[0]);
		assertEquals(true,er[1]);
		assertEquals(false,er[2]);
		assertEquals(true,er[3]);
		assertEquals(false,er[4]);
		
		assertEquals(true,ir[0]);
		assertEquals(true,ir[1]);
		assertEquals(true,ir[2]);
		assertEquals(true,ir[3]);
		assertEquals(false,ir[4]);
		
		assertEquals(false,dup[0]);
		assertEquals(false,dup[1]);
		assertEquals(false,dup[2]);
		assertEquals(true,dup[3]);
		assertEquals(false,dup[4]);
		
		int a = UserControl.searchID("161188100");
		assertEquals(0,a);
		
	}



}
