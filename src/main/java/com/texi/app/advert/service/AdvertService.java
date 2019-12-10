package com.texi.app.advert.service;

import com.texi.app.domain.Advert;
import com.texi.app.domain.User;

import java.util.List;

public interface AdvertService {

	public void save(Advert advert);
	public List<Advert> findAll();
	public void update(Long id, Advert advert);
}
