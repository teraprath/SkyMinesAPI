package net.blockyislands.api;

import javax.annotation.Nonnull;
import java.util.UUID;

public interface IBlockyAPI {

    public void setBalance(@Nonnull UUID uuid, int amount);
    public int getBalance(@Nonnull UUID uuid);
    public void setKills(@Nonnull UUID uuid, int amount);
    public int getKills(@Nonnull UUID uuid);
    public void setDeaths(@Nonnull UUID uuid, int amount);
    public int getDeaths(@Nonnull UUID uuid);


}
