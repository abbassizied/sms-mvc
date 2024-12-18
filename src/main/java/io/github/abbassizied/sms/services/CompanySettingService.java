package io.github.abbassizied.sms.services;

import io.github.abbassizied.sms.entities.CompanySetting;

public interface CompanySettingService {
    CompanySetting getOrCreateSetting();
    void saveOrUpdate(CompanySetting companySetting);
}

 
