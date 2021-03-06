
# Uncomment the following line to enable the hard floating point abi. Note that
# this breaks some binary libraries and 3D (neither of which ship with
# meta-yocto). For maximum compatibility, leave this disabled.
DEFAULTTUNE ?= "cortexa9hf-neon"
require conf/machine/include/tune-cortexa9.inc

SOC_FAMILY="netx4000"
require conf/machine/include/soc-family.inc

#############################################################################
# Bootloader
#############################################################################
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

PREFERRED_PROVIDER_virtual/bootloader ??= "u-boot"
UBOOT_MACHINE ??= "netx4000_defconfig"
UBOOT_ENTRYPOINT ?= "0x40008000"

PREFERRED_PROVIDER_virtual/kernel ??= "linux-stable"

PREFERRED_PROVIDER_virtual/dtb ??= "device-tree"
MACHINE_ESSENTIAL_EXTRA_RDEPENDS_append += "device-tree"

#############################################################################
# Kernel
#############################################################################
KERNEL_CLASSES += "kernel-fitimage"
KERNEL_IMAGETYPES_append += "fitImage"
MACHINE_ESSENTIAL_EXTRA_RDEPENDS_append +=" \
	kernel-modules \
	libcifx cifxsample \
	dto-utils \
	kernel-module-netx4000-xc-stdmac kernel-module-mdio-xc-netx4000 hethmac \
"

# Kernel default config does not set default parameters and yocto uses allnoconfig
# per default, thus we need to override it to use alldefconfig
KCONFIG_MODE ??= "--alldefconfig"

# poky-lsb requires nfsd in kernel
KERNEL_EXTRA_FEATURES_append += " \
	${@ 'features/nfsd/nfsd-enable.scc' if '${DISTRO}' == 'poky-lsb' else '' } \
	features/firmware/firmware.scc \
"

#############################################################################
# Contents of boot partition (excluding rootfs)
#############################################################################
IMAGE_BOOT_FILES ??= "netx.rom fitImage-${MACHINE}.bin;fitImage"

WKS_FILE ?= "sdimage-bootpart.wks"
IMAGE_FSTYPES ?= "wic.bz2 wic.bmap"
