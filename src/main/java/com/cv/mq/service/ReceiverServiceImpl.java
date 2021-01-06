/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.mq.service;

import com.cv.accountswing.common.ReloadData;
import com.cv.accountswing.entity.AppUser;
import com.cv.accountswing.entity.ChartOfAccount;
import com.cv.accountswing.entity.CompanyInfo;
import com.cv.accountswing.entity.Currency;
import com.cv.accountswing.entity.Customer;
import com.cv.accountswing.entity.Department;
import com.cv.accountswing.entity.Menu;
import com.cv.accountswing.entity.Privilege;
import com.cv.accountswing.entity.Region;
import com.cv.accountswing.entity.Supplier;
import com.cv.accountswing.entity.SystemProperty;
import com.cv.accountswing.entity.Trader;
import com.cv.accountswing.entity.TraderType;
import com.cv.accountswing.entity.UserRole;
import com.cv.accountswing.entity.UsrCompRole;
import com.cv.accountswing.service.AccountService;
import com.cv.accountswing.service.COAService;
import com.cv.accountswing.service.CompanyInfoService;
import com.cv.accountswing.service.CurrencyService;
import com.cv.accountswing.service.CustomerService;
import com.cv.accountswing.service.DepartmentService;
import com.cv.accountswing.service.MenuService;
import com.cv.accountswing.service.PaymentTypeService;
import com.cv.accountswing.service.PrivilegeService;
import com.cv.accountswing.service.RegionService;
import com.cv.accountswing.service.SupplierService;
import com.cv.accountswing.service.SystemPropertyService;
import com.cv.accountswing.service.TraderService;
import com.cv.accountswing.service.TraderTypeService;
import com.cv.accountswing.service.UserRoleService;
import com.cv.accountswing.service.UsrCompRoleService;
import com.cv.inv.entity.Category;
import com.cv.inv.entity.CharacterNo;
import com.cv.inv.entity.ChargeType;
import com.cv.inv.entity.Location;
import com.cv.inv.entity.MachineInfo;
import com.cv.inv.entity.SaleHis;
import com.cv.inv.entity.Stock;
import com.cv.inv.entity.StockBrand;
import com.cv.inv.entity.StockType;
import com.cv.inv.entity.StockUnit;
import com.cv.inv.entity.UnitPattern;
import com.cv.inv.entity.UnitRelation;
import com.cv.inv.entity.VouStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cv.inv.service.CategoryService;
import com.cv.inv.service.CharacterNoService;
import com.cv.inv.service.ChargeTypeService;
import com.cv.inv.service.LocationService;
import com.cv.inv.service.MachineInfoService;
import com.cv.inv.service.RelationService;
import com.cv.inv.service.SaleHisService;
import com.cv.inv.service.SaleManService;
import com.cv.inv.service.StockBrandService;
import com.cv.inv.service.StockService;
import com.cv.inv.service.StockTypeService;
import com.cv.inv.service.StockUnitService;
import com.cv.inv.service.UnitPatternService;
import com.cv.inv.service.VouStatusService;
import com.google.gson.JsonSyntaxException;

/**
 *
 * @author SAI
 */
@Service
public class ReceiverServiceImpl implements ReceiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverServiceImpl.class);
    private final Gson gson = new GsonBuilder().
            setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
    @Autowired
    private PaymentTypeService ptService;
    @Autowired
    private VouStatusService vouService;
    @Autowired
    private CurrencyService currService;

    @Autowired
    private TraderService traderService;
    @Autowired
    private CustomerService cusService;
    @Autowired
    private SupplierService supService;
    @Autowired
    private UserRoleService urService;
    @Autowired
    private LocationService locService;
    @Autowired
    private StockTypeService stService;
    @Autowired
    private CategoryService cService;
    @Autowired
    private StockBrandService sbService;
    @Autowired
    private StockUnitService suService;
    @Autowired
    private ChargeTypeService ctService;
    @Autowired
    private StockService stockService;
    @Autowired
    private MachineInfoService miService;
    @Autowired
    private SystemPropertyService spService;
    @Autowired
    private MenuService m2Service;
    @Autowired
    private PrivilegeService prvService;
    @Autowired
    private AccountService auService;
    @Autowired
    private RegionService regService;
    @Autowired
    private DepartmentService deptService;
    @Autowired
    private SaleManService smService;
    @Autowired
    private RelationService unitrService;
    @Autowired
    private CharacterNoService cnService;
    @Autowired
    private COAService coaService;
    @Autowired
    private UnitPatternService upService;
    @Autowired
    private TraderTypeService ttService;
    @Autowired
    private UsrCompRoleService ucrService;
    @Autowired
    private CompanyInfoService cifService;
    @Autowired
    private SaleHisService shService;
    private boolean syncFinish = false;

    public boolean isSyncFinish() {
        return syncFinish;
    }

    public void setSyncFinish(boolean syncFinish) {
        this.syncFinish = syncFinish;
    }

    @Override
    public void doReceiverOperation(ReloadData rlData, String operationId, String entityType, Object data) {

        switch (operationId) {
            case "SALE-VOUSEARCH":
                switch (entityType) {
                    case "ACK":
                        if (rlData != null) {
                            rlData.reload(entityType, operationId);
                        }
                        break;
                    default:
                        if (rlData != null) {
                            rlData.reload(operationId, data);
                        }
                        break;
                }
                break;

            case "GET-SALEVOUCHER":
                //SaleHis sh = gson.fromJson(data.toString(), SaleHis.class);
                if (rlData != null) {
                    rlData.reload(operationId, data);
                }
                break;

            default:
                doEntityOperation(rlData, operationId, entityType, data);
                break;
        }

    }

    private void doEntityOperation(ReloadData rlData, String operationId, String entityType, Object data) {

        switch (entityType) {
            case "AppUser":
                try {
                AppUser au;
                if (data instanceof String) {
                    au = gson.fromJson(data.toString(), AppUser.class);
                } else {
                    au = (AppUser) data;
                }
                auService.saveAccount(au);
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperation AppUser : " + ex.getMessage());
            }
            break;

            case "Region":
                try {
                Region re;
                if (data instanceof String) {
                    re = gson.fromJson(data.toString(), Region.class);
                } else {
                    re = (Region) data;
                }
                regService.save(re);
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperation Region : " + ex.getMessage());
            }
            break;

            case "Department":
                try {
                Department de;
                if (data instanceof String) {
                    de = gson.fromJson(data.toString(), Department.class);
                } else {
                    de = (Department) data;
                }
                deptService.save(de);
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperation Department : " + ex.getMessage());
            }
            break;

            case "Currency":
                try {
                Currency curr;
                if (data instanceof String) {
                    curr = gson.fromJson(data.toString(), Currency.class);
                } else {
                    curr = (Currency) data;
                }
                currService.save(curr);
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperation Currency : " + ex.getMessage());
            }
            break;

            case "Location":
                try {
                Location loc;
                if (data instanceof String) {
                    loc = gson.fromJson(data.toString(), Location.class);
                } else {
                    loc = (Location) data;
                }
                locService.save(loc);
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperation Location : " + ex.getMessage());
            }
            break;

            case "VouStatus":
                try {
                VouStatus vous;
                if (data instanceof String) {
                    vous = gson.fromJson(data.toString(), VouStatus.class);
                } else {
                    vous = (VouStatus) data;
                }
                vouService.save(vous);
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperation VouStatus : " + ex.getMessage());
            }
            break;

            case "ACK":
                LOGGER.info(operationId + " : " + data.toString());
                switch (operationId) {
                    case "NEW-INIT":
                        syncFinish = true;
                        break;
                }
                break;
                
                   case "ACK-SaveSale":
                String invId = data.toString();
                try {
                    SaleHis shh2 = shService.findById(invId);
                    //shh2.setIntgUpdStatus("SSAVE");
                    shService.save(shh2);
                } catch (Exception ex) {
                    LOGGER.error("doEntityOperation ACK-SaveSale : " + invId + " : " + ex.getMessage());
                }
                break;

        }

    }

    @Override
    public void doReceiverFileOperation(ReloadData rlData, String operationId, String entityType, JsonElement data) {
        switch (operationId) {
            case "SALE-VOUSEARCH":
                switch (entityType) {
                    case "ACK":
                        if (rlData != null) {
                            rlData.reload(entityType, operationId);
                        }
                        break;
                    default:
                        if (rlData != null) {
                            rlData.reload(operationId, data);
                        }
                        break;
                }
                break;

            default:
                doEntityOperationFile(rlData, operationId, entityType, data);

                break;
        }
    }

    private void doEntityOperationFile(ReloadData rlData, String operationId,
            String entityType, JsonElement data) {
        switch (entityType) {
            /*case "PaymentType":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<PaymentTypeH2>>() {
                        }.getType();

                List<PaymentTypeH2> ListPayType = gson.fromJson(data, collectionType);
                for (PaymentTypeH2 pt : ListPayType) {
                    ptService.save(pt);
                    LOGGER.info("PaymentType:" + pt.getTypeId());
                }
            } catch (Exception ex) {
                LOGGER.error("doEntityOperationFile PaymentType : " + ex.getMessage());
            }
            break;*/
            case "Appuser":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<AppUser>>() {
                        }.getType();

                List<AppUser> ListAppUser = gson.fromJson(data, collectionType);

                ListAppUser.stream().map(app -> {
                    auService.saveAccount(app);
                    return app;
                }).forEachOrdered(app -> {
                    LOGGER.info("Appuser:" + app.getUserCode() + "," + app.getUserName());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile Appuser : " + ex.getMessage());
            }
            break;
            case "CompanyInfo":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<CompanyInfo>>() {
                        }.getType();

                List<CompanyInfo> ListCIFH2 = gson.fromJson(data, collectionType);

                ListCIFH2.stream().map(cif -> {
                    cifService.save(cif);
                    return cif;
                }).forEachOrdered(cif -> {
                    LOGGER.info("CompanyInfo:" + cif.getCompCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile CompanyInfo : " + ex.getMessage());
            }
            break;

            case "UsrCompRole":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<UsrCompRole>>() {
                        }.getType();

                List<UsrCompRole> ListUCRH2 = gson.fromJson(data, collectionType);

                ListUCRH2.stream().map(uc -> {
                    ucrService.save(uc);
                    return uc;
                }).forEachOrdered(uc -> {
                    LOGGER.info("UsrCompRole:" + uc.getKey().getRoleCode() + "," + uc.getKey().getUserCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile UsrCompRole : " + ex.getMessage());
            }
            break;

            case "Region":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<Region>>() {
                        }.getType();

                List<Region> ListRegion = gson.fromJson(data, collectionType);

                ListRegion.stream().map(re -> {
                    regService.save(re);
                    return re;
                }).forEachOrdered(re -> {
                    LOGGER.info("Region:" + re.getRegCode() + "," + re.getRegionName());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile Region : " + ex.getMessage());
            }
            break;

            case "Department":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<Department>>() {
                        }.getType();

                List<Department> ListDepart = gson.fromJson(data, collectionType);

                ListDepart.stream().map(de -> {
                    deptService.save(de);
                    return de;
                }).forEachOrdered(de -> {
                    LOGGER.info("Department:" + de.getDeptCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile Department : " + ex.getMessage());
            }
            break;

            case "Currency":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<Currency>>() {
                        }.getType();

                List<Currency> ListCurr = gson.fromJson(data, collectionType);
                ListCurr.stream().map(curr -> {
                    currService.save(curr);
                    return curr;
                }).forEachOrdered(curr -> {
                    LOGGER.info("Currency:" + curr.getKey().getCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile Currency : " + ex.getMessage());
            }
            break;

            case "Location":
                try {

                java.lang.reflect.Type collectionType
                        = new TypeToken<List<Location>>() {
                        }.getType();

                List<Location> ListLocH2 = gson.fromJson(data.toString(), collectionType);

                ListLocH2.stream().map(loc -> {
                    locService.save(loc);
                    return loc;
                }).forEachOrdered(loc -> {
                    LOGGER.info("Location:" + loc.getLocationCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile Location : " + ex.getMessage());
            }
            break;

            case "VouStatus":
                try {

                java.lang.reflect.Type collectionType
                        = new TypeToken<List<VouStatus>>() {
                        }.getType();

                List<VouStatus> ListVouStatus = gson.fromJson(data, collectionType);
                ListVouStatus.stream().map(pt -> {
                    vouService.save(pt);
                    return pt;
                }).forEachOrdered(pt -> {
                    LOGGER.info("VouStatus:" + pt.getVouStatusCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile VouStatus : " + ex.getMessage());
            }
            break;

            case "UnitRelation":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<UnitRelation>>() {
                        }.getType();

                List<UnitRelation> ListURH2 = gson.fromJson(data, collectionType);
                ListURH2.stream().map(ur -> {
                    unitrService.save(ur);
                    return ur;
                }).forEachOrdered(ur -> {
                    LOGGER.info("UnitRelation: " + ur.getUnitKey().getPatternId());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile UnitRelation : " + ex.getMessage());
            }
            break;

            case "ChargeType":
                try {

                java.lang.reflect.Type collectionType
                        = new TypeToken<List<ChargeType>>() {
                        }.getType();

                List<ChargeType> ListCTH2 = gson.fromJson(data, collectionType);

                ListCTH2.stream().map(cth -> {
                    ctService.save(cth);
                    return cth;
                }).forEachOrdered(cth -> {
                    LOGGER.info("ChargeType: " + cth.getChargeTypeCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile ChargeType : " + ex.getMessage());
            }
            break;

            case "StockType":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<StockType>>() {
                        }.getType();

                List<StockType> ListSTH2 = gson.fromJson(data, collectionType);

                ListSTH2.stream().map(it -> {
                    stService.save(it);
                    return it;
                }).forEachOrdered(it -> {
                    LOGGER.info("StockType: " + it.getItemTypeCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile StockType : " + ex.getMessage());
            }
            break;

            case "Category":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<Category>>() {
                        }.getType();

                List<Category> ListCatH2 = gson.fromJson(data, collectionType);

                ListCatH2.stream().map(cat -> {
                    cService.save(cat);
                    return cat;
                }).forEachOrdered(cat -> {
                    LOGGER.info("Category:" + cat.getCatCode() + "," + cat.getCatName());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile Category : " + ex.getMessage());
            }
            break;

            case "StockBrand":
                try {

                java.lang.reflect.Type collectionType
                        = new TypeToken<List<StockBrand>>() {
                        }.getType();

                List<StockBrand> ListSBH2 = gson.fromJson(data, collectionType);

                ListSBH2.stream().map(ib -> {
                    sbService.save(ib);
                    return ib;
                }).forEachOrdered(ib -> {
                    LOGGER.info("StockBrand: " + ib.getBrandCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile StockBrand : " + ex.getMessage());
            }
            break;

            case "CharacterNo":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<CharacterNo>>() {
                        }.getType();

                List<CharacterNo> ListCNH2 = gson.fromJson(data, collectionType);
                ListCNH2.stream().map(cn -> {
                    cnService.save(cn);
                    return cn;
                }).forEachOrdered(cn -> {
                    LOGGER.info("CharacterNo: " + cn.getStrNumber());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile CharacterNo : " + ex.getMessage());
            }
            break;

            case "StockUnit":
                try {

                java.lang.reflect.Type collectionType
                        = new TypeToken<List<StockUnit>>() {
                        }.getType();

                List<StockUnit> ListSUH2 = gson.fromJson(data, collectionType);

                ListSUH2.stream().map(iu -> {
                    suService.save(iu);
                    return iu;
                }).forEachOrdered(iu -> {
                    LOGGER.info("StockUnit: " + iu.getItemUnitCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile StockUnit : " + ex.getMessage());
            }
            break;

            case "UnitPattern":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<UnitPattern>>() {
                        }.getType();

                List<UnitPattern> ListUPH2 = gson.fromJson(data, collectionType);
                ListUPH2.stream().map(up -> {
                    upService.save(up);
                    return up;
                }).forEachOrdered(up -> {
                    LOGGER.info("UnitPattern: " + up.getPatternCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile UnitPattern : " + ex.getMessage());
            }
            break;

            case "Stock":

                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<Stock>>() {
                        }.getType();

                List<Stock> ListStock = gson.fromJson(data, collectionType);
                // LOGGER.info("Stock Size:" + ListStock.size());
                ListStock.stream().map(sk -> {
                    stockService.save(sk);
                    return sk;
                }).forEachOrdered(sk -> {
                    LOGGER.info("Stock  :" + sk.getStockCode());
                });
                boolean isNull = false;
                if (rlData == null) {
                    isNull = true;
                }
                LOGGER.info("Check : " + operationId + "," + isNull);
                if (operationId.equals("SYNC-STOCK")) {
                    if (rlData != null) {
                        rlData.reload(operationId, ListStock);
                    }
                }
                LOGGER.info("doEntityOperationFile Medicine : " + ListStock.get(0) + " - " + ListStock.get(1));

            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile Medicine : " + ex.getMessage());
            }
            break;

            case "ChartOfAccount":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<ChartOfAccount>>() {
                        }.getType();

                List<ChartOfAccount> ListCOAH2 = gson.fromJson(data, collectionType);
                ListCOAH2.stream().map(coa -> {
                    coaService.save(coa);
                    return coa;
                }).forEachOrdered(coa -> {
                    LOGGER.info("ChartOfAccount:" + coa.getCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile ChartOfAccount : " + ex.getMessage());
            }
            break;

            case "TraderType":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<TraderType>>() {
                        }.getType();

                List<TraderType> ListTTH2 = gson.fromJson(data, collectionType);
                ListTTH2.stream().map(tt -> {
                    ttService.save(tt);
                    return tt;
                }).forEachOrdered(tt -> {
                    LOGGER.info("TraderType:" + tt.getTypeId());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile TraderType : " + ex.getMessage());
            }
            break;

            /*case "SaleMan":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<SaleManH2>>() {
                        }.getType();

                List<SaleManH2> ListSMH2 = gson.fromJson(data, collectionType);
                for (SaleManH2 sm : ListSMH2) {
                    smService.save(sm);
                    LOGGER.info("SaleMan:" + sm.getSaleManId());
                }
            } catch (Exception ex) {
                LOGGER.error("doEntityOperationFile SaleMan : " + ex.getMessage());
            }
            break;*/
            case "SystemProperty":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<SystemProperty>>() {
                        }.getType();

                List<SystemProperty> ListSPH2 = gson.fromJson(data, collectionType);
                ListSPH2.stream().map(sp -> {
                    spService.save(sp);
                    return sp;
                }).forEachOrdered(sp -> {
                    LOGGER.info("SystemProperty:" + sp.getPropValue());
                }); //LOGGER.info(sp.getPropDesp());
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperation SystemProperty : " + ex.getMessage());
            }
            break;

            case "Trader":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<Trader>>() {
                        }.getType();
                List<Trader> ListTraderH2 = gson.fromJson(data, collectionType);
                int count = 0;

                for (Trader t : ListTraderH2) {
                    String tmp = t.getCode().substring(0, 3);
                    switch (tmp.toUpperCase()) {
                        case "CUS":
                            Customer cus = new Customer();
                            cus.setCode(t.getCode());
                            cus.setUserCode(t.getUserCode());
                            cus.setCompCode(t.getCompCode());
                            cus.setTraderName(t.getTraderName());
                            cus.setAddress(t.getAddress());
                            cus.setRegion(t.getRegion());
                            cus.setTraderType(t.getTraderType());
                            cus.setPhone(t.getPhone());
                            cus.setEmail(t.getEmail());
                            cus.setCreatedBy(t.getCreatedBy());
                            cus.setCreatedDate(t.getCreatedDate());
                            cus.setAccount(t.getAccount());
                            cus.setActive(t.getActive());
                            cus.setRemark(t.getRemark());
                            cus.setParent(t.getParent());
                            cus.setAppShortName(t.getAppShortName());
                            cus.setAppTraderCode(t.getAppTraderCode());

                            cusService.save(cus);
                            break;
                        case "SUP":
                            Supplier s = new Supplier();
                            s.setCode(t.getCode());
                            s.setUserCode(t.getUserCode());
                            s.setCompCode(t.getCompCode());
                            s.setTraderName(t.getTraderName());
                            s.setAddress(t.getAddress());
                            s.setRegion(t.getRegion());
                            s.setTraderType(t.getTraderType());
                            s.setPhone(t.getPhone());
                            s.setEmail(t.getEmail());
                            s.setCreatedBy(t.getCreatedBy());
                            s.setCreatedDate(t.getCreatedDate());
                            s.setAccount(t.getAccount());
                            s.setActive(t.getActive());
                            s.setRemark(t.getRemark());
                            s.setParent(t.getParent());
                            s.setAppShortName(t.getAppShortName());
                            s.setAppTraderCode(t.getAppTraderCode());

                            supService.save(s);
                            break;
                        default:
                            traderService.saveTrader(t);
                            break;

                    }
                    LOGGER.info("Trader:" + t.getUserCode());
                }

            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile Trader : " + ex.getMessage());
            }
            break;

            case "Menu":
                try {

                java.lang.reflect.Type collectionType
                        = new TypeToken<List<Menu>>() {
                        }.getType();
                List<Menu> ListMenu = gson.fromJson(data, collectionType);

                ListMenu.stream().map(mh2 -> {
                    m2Service.saveMenu(mh2);
                    return mh2;
                }).forEachOrdered(mh2 -> {
                    LOGGER.info("Menu:" + mh2.getCode() + "," + mh2.getMenuName());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile Menu : " + ex.getMessage());
            }
            break;

            case "UserRole":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<UserRole>>() {
                        }.getType();

                List<UserRole> ListURoleH2 = gson.fromJson(data, collectionType);

                ListURoleH2.stream().map(ur -> {
                    urService.save(ur);
                    return ur;
                }).forEachOrdered(ur -> {
                    LOGGER.info("UserRole:" + ur.getRoleCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile UserRole : " + ex.getMessage());
            }
            break;

            case "MachineInfo":
                try {
                java.lang.reflect.Type collectionType
                        = new TypeToken<List<MachineInfo>>() {
                        }.getType();

                List<MachineInfo> ListMIH2 = gson.fromJson(data, collectionType);
                for (MachineInfo mi : ListMIH2) {
                    miService.save(mi);
                    LOGGER.info("MachineInfo:" + mi.getMachineId());
                }
            } catch (Exception ex) {
                LOGGER.error("doEntityOperationFile MachineInfo : " + ex.getMessage());
            }
            break;
            case "Privilege":
                try {

                java.lang.reflect.Type collectionType
                        = new TypeToken<List<Privilege>>() {
                        }.getType();

                List<Privilege> medicine = gson.fromJson(data, collectionType);

                medicine.stream().map(prvh2 -> {
                    prvService.save(prvh2);
                    return prvh2;
                }).forEachOrdered(prvh2 -> {
                    LOGGER.info("Privilege:" + prvh2.getKey().getRoleCode());
                });
            } catch (JsonSyntaxException ex) {
                LOGGER.error("doEntityOperationFile Privilege : " + ex.getMessage());
            }
            break;

        }
    }
}
