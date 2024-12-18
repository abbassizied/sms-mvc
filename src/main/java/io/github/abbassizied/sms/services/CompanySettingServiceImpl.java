package io.github.abbassizied.sms.services;

import io.github.abbassizied.sms.entities.CompanySetting;
import io.github.abbassizied.sms.repositories.CompanySettingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanySettingServiceImpl implements CompanySettingService {

	@Autowired
    private CompanySettingRepository companySettingRepository;
 
    /**
     * This method ensures that there is only one record in the CompanySetting table.
     * If no record exists, it creates a new one with default values.
     * All operations on this table should only create or update this single record.
     */	
    @Override
    public CompanySetting getOrCreateSetting() {
        return companySettingRepository.findFirst().orElseGet(() -> {
            CompanySetting setting = new CompanySetting();
            setting.setCompanyName("Default Company Name");
            return companySettingRepository.save(setting);
        });
    }
    

    @Override
    public void saveOrUpdate(CompanySetting companySetting) {
        companySettingRepository.save(companySetting);
    }    
}
