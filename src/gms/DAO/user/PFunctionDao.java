package gms.DAO.user;


import gms.entry.user.PFunction;

import java.util.List;


public interface PFunctionDao {
	void insertPFunction(PFunction pfunction);
	List<PFunction> pfunctionList();
}