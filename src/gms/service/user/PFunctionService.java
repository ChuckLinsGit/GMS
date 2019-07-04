package gms.service.user;

import gms.entry.user.PFunction;

import java.util.List;


public interface PFunctionService {

	void addPFunction(PFunction pfunction);
	List<PFunction> pfunctionList();
}